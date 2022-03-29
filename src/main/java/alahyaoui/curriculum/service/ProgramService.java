package alahyaoui.curriculum.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
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

    // This is a lambda expression that is used to sort the courses by their section.
    Comparator<Course> sectionComparator = (course1, course2) -> course1.getSection().compareTo(course2.getSection());

    // This is a lambda expression that is used to sort the courses by their id.
    Comparator<Course> idComparator = (course1, course2) -> course1.getId().compareTo(course2.getId());

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

        annualStudentProgram.sort(sectionComparator);
        return annualStudentProgram;
    }

    /**
     * If all prerequisites are passed and all corequisites are accessible, then the course is
     * accessible
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
     * Given a student program and a course node, return true if all the prerequisites of the course
     * node have been passed
     * 
     * @param studentProgram The student's program.
     * @param courseNode The course node that we are trying to evaluate.
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
     * Given a student program and a course node, return true if all of the corequisites of the course
     * node are accessible to the student
     * 
     * @param studentProgram The student's program.
     * @param courseNode The course node that we are trying to add to the student program.
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
}
