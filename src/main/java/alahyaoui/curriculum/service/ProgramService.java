package alahyaoui.curriculum.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import alahyaoui.curriculum.business.CourseGraph;
import alahyaoui.curriculum.business.CourseNode;
import alahyaoui.curriculum.business.Program;
import alahyaoui.curriculum.dto.CourseStateDto;
import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.model.Section;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgramService {

    @Autowired
    private final CourseService courseService;

    @Autowired
    private final CourseGraph courseGraph;

    Comparator<Course> sectionComparator = (course1, course2) -> course1.getSection().compareTo(course2.getSection());

    Comparator<Course> idComparator = (course1, course2) -> course1.getId().compareTo(course2.getId());

    public void init() throws NumberFormatException, CsvValidationException, IOException {
        initGraph();
        initPrerequisites();
        initCorequisites();
    }

    private void initGraph() {
        var courses = courseService.getAllCourses();
        for (var course : courses) {
            CourseNode node = new CourseNode(course.getId());
            courseGraph.addNode(node);
        }
    }

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

    public Program getStudentProgram(Section section) {
        List<Course> courses = courseService.getSectionCourses(section);
        return new Program(courses);
    }

    public List<Course> getAnnualStudentProgram(Program program) {
        List<Course> annualStudentProgram = new ArrayList<>();
        for (var entry : program.getCoursesToStates().entrySet()) {
            CourseStateDto courseState = entry.getValue();
            if (courseState.getIsAccessible()) {
                Course course = courseService.getCourseById(entry.getKey());
                annualStudentProgram.add(course);
            }
        }

        annualStudentProgram.sort(sectionComparator);
        return annualStudentProgram;
    }

    public void updateProgram(Program studentProgram) {
        for (var entry : studentProgram.getCoursesToStates().entrySet()) {
            CourseStateDto courseState = entry.getValue();
            String courseId = entry.getKey();
            CourseNode courseNode = courseGraph.search(courseId);

            if (courseState.getIsPassed()) {
                courseState.setIsAccessible(false);
            } else {
                if (areAllPrerequisitesPassed(studentProgram, courseNode)
                        && areAllCorequisitesAccessible(studentProgram, courseNode)) {
                    courseState.setIsAccessible(true);
                }
            }
        }
    }

    private boolean areAllPrerequisitesPassed(Program studentProgram, CourseNode courseNode) {
        var prerequisites = courseNode.getPrerequisites();
        for (var prerequisite : prerequisites) {
            String courseId = prerequisite.getId();
            CourseStateDto courseState = studentProgram.getStudentProgramCourse(courseId);
            if (courseState.getIsPassed() == false) {
                return false;
            }
        }
        return true;
    }

    private boolean areAllCorequisitesAccessible(Program studentProgram, CourseNode courseNode) {
        var corequisites = courseNode.getCorequisites();
        for (var corequisite : corequisites) {
            String courseId = corequisite.getId();
            CourseStateDto courseState = studentProgram.getStudentProgramCourse(courseId);
            if (courseState.getIsAccessible() == false) {
                return false;
            }
        }
        return true;
    }
}
