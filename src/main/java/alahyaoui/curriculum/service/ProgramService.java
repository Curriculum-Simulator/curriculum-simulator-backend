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
import alahyaoui.curriculum.business.Program;
import alahyaoui.curriculum.dto.CourseDto;
import alahyaoui.curriculum.dto.CourseStateDto;
import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.model.Section;
import alahyaoui.curriculum.util.HashMapUtil;
import alahyaoui.curriculum.util.ComparatorUtil;
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
    public Program getStudentProgram(Section section) {
        List<Course> courses = courseService.getSectionCourses(section);
        return new Program(courses);
    }

    /**
     * Get the list of courses that are accessible to the student in the program
     * 
     * @param program The program that the student is enrolled in.
     * @return The list of courses that the student can take in the program.
     */
    public List<Course> getAnnualStudentProgram(Program program) {
        List<Course> annualStudentProgram = new ArrayList<>();
        for (var entry : program.getCoursesToStates().entrySet()) {
            CourseStateDto courseState = entry.getValue();
            if (courseState.isAccessible()) {
                Course course = courseService.getCourseById(entry.getKey());
                annualStudentProgram.add(course);
            }
        }

        annualStudentProgram.sort(ComparatorUtil.COURSE_COMPARATOR);
        return annualStudentProgram;
    }

    /**
     * If all prerequisites are passed and all corequisites are accessible, 
     * then the course is accessible
     * 
     * @param studentProgram The student's program.
     */
    public void updateProgram(Program studentProgram) {
        for (var entry : studentProgram.getCoursesToStates().entrySet()) {
            CourseStateDto courseState = entry.getValue();
            String courseId = entry.getKey();
            CourseNode courseNode = courseGraph.search(courseId);

            if (courseState.isPassed()) {
                courseState.setAccessible(false);
            } else {
                if (areAllPrerequisitesPassed(studentProgram, courseNode)
                        && areAllCorequisitesAccessible(studentProgram, courseNode)) {
                    courseState.setAccessible(true);
                }
            }
        }
    }

    /**
     * Given a student program and a course node, return true if all the
     * prerequisites of the course node have been passed
     * 
     * @param studentProgram The student's program.
     * @param courseNode     The course node that we are trying to evaluate.
     * @return The method returns a boolean value.
     */
    private boolean areAllPrerequisitesPassed(Program studentProgram, CourseNode courseNode) {
        var prerequisites = courseNode.getPrerequisites();
        for (var prerequisite : prerequisites) {
            String courseId = prerequisite.getId();
            CourseStateDto courseState = studentProgram.getStudentProgramCourse(courseId);
            if (courseState.isPassed() == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Given a student program and a course node, return true if all of the
     * corequisites of the course node are accessible to the student
     * 
     * @param studentProgram The student's program.
     * @param courseNode The course node that we are trying to add to the student program.
     *                       
     * @return The method returns a boolean value.
     */
    private boolean areAllCorequisitesAccessible(Program studentProgram, CourseNode courseNode) {
        var corequisites = courseNode.getCorequisites();
        for (var corequisite : corequisites) {
            String courseId = corequisite.getId();
            CourseStateDto courseState = studentProgram.getStudentProgramCourse(courseId);
            if (courseState.isAccessible() == false) {
                return false;
            }
        }
        return true;
    }

    /*** For rest api (because passing a program object encapsulating a hashmap is not practical) ***/

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

    public void updateProgram(ArrayList<CourseDto> studentProgram) {
        HashMap<String, CourseDto> idToCourse = HashMapUtil.convertArrayListToHashMap(studentProgram);
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
