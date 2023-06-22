package alahyaoui.curriculum.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import alahyaoui.curriculum.business.CourseGraph;
import alahyaoui.curriculum.business.CourseNode;
import alahyaoui.curriculum.dto.CourseDto;
import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.model.Section;
import alahyaoui.curriculum.util.HashMapUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgramService {

    @Autowired
    private final CourseService courseService;

    @Autowired
    private final CourseGraph courseGraph;

    /**
     * Initialize the graph and prerequisites and corequisites
     */
    @PostConstruct
    private void init() throws NumberFormatException, CsvValidationException, IOException {
        initGraph();
        initPrerequisites();
        initCorequisites();
    }

    /**
     * Initialize the graph by adding all the courses to it
     */
    private void initGraph() {
        courseGraph.clear();
        var courses = courseService.getAllCourses();
        for (var course : courses) {
            CourseNode node = new CourseNode(course.getId());
            courseGraph.addNode(node);
        }
    }

    /**
     * Reads in the prerequisites.csv file and adds the prerequisites to the course graph
     */
    private void initPrerequisites() throws NumberFormatException, CsvValidationException, IOException {
        String path = "static/data/prerequisites.csv";
        var fileReader = new InputStreamReader(new ClassPathResource(path).getInputStream());
        try (CSVReader reader = new CSVReader(fileReader)) {
            String[] values;
            while ((values = reader.readNext()) != null) {
                CourseNode courseNode = courseGraph.search(values[0]);
                CourseNode prerequisiteNode = courseGraph.search(values[1]);
                courseNode.addPrerequisite(prerequisiteNode);
            }
        }
    }

    /**
     * Reads in the corequisites.csv file and adds the corequisites to the course nodes
     */
    private void initCorequisites() throws NumberFormatException, CsvValidationException, IOException {
        String path = "static/data/corequisites.csv";
        var fileReader = new InputStreamReader(new ClassPathResource(path).getInputStream());
        try (CSVReader reader = new CSVReader(fileReader)) {
            String[] values;
            while ((values = reader.readNext()) != null) {
                CourseNode courseNode = courseGraph.search(values[0]);
                CourseNode corequisiteNode = courseGraph.search(values[1]);
                courseNode.addCorequisite(corequisiteNode);
            }
        }
    }

    /**
     * Get the program for a section
     * 
     * @param section The section to get the program for.
     * @return A Program object.
     */
    public List<CourseDto> getProgram(Section section) {
        List<Course> courses = courseService.getSectionCourses(section);
        List<CourseDto> program = new ArrayList<CourseDto>();
        for (Course course : courses){
            program.add(new CourseDto(course));
        }
        return program;
    }

    public void updateProgram(List<CourseDto> studentProgram) {
        HashMap<String, CourseDto> idToCourse = HashMapUtil.convertListToHashMap(studentProgram);
        for (var course : studentProgram) {
            CourseNode courseNode = courseGraph.search(course.getId());

            if (course.isPassed()) {
                course.setAccessible(false);
            } else {
                if (areAllPrerequisitesPassed(idToCourse, courseNode)
                        && areAllCorequisitesAccessible(idToCourse, courseNode)) {
                    course.setAccessible(true);
                }
            }
        }
    }
    
    private boolean areAllPrerequisitesPassed(HashMap<String, CourseDto> studentProgram, CourseNode courseNode) {
        var prerequisites = courseNode.getPrerequisites();
        for (var prerequisite : prerequisites) {
            String prerequisiteId = prerequisite.getId();
            CourseDto courseState = studentProgram.get(prerequisiteId);
            if (courseState.isPassed() == false) {
                return false;
            }
        }
        return true;
    }

    private boolean areAllCorequisitesAccessible(HashMap<String, CourseDto> studentProgram, CourseNode courseNode) {
        var corequisites = courseNode.getCorequisites();
        for (var corequisite : corequisites) {
            String corequisiteId = corequisite.getId();
            CourseDto courseState = studentProgram.get(corequisiteId);
            if (courseState.isAccessible() == false) {
                return false;
            }
        }
        return true;
    }
}
