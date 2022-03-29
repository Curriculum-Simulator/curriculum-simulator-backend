package alahyaoui.curriculum.business;

import java.util.HashMap;
import java.util.List;

import alahyaoui.curriculum.dto.CourseStateDto;
import alahyaoui.curriculum.model.Course;
import lombok.Value;

/**
 * This class represents a student's program, it links the state of a student's
 * courses with the courses.
 * 
 * @author Ayoub Lahyaoui
 */
@Value
public class Program {

    private final HashMap<String, CourseStateDto> coursesToStates;

    // Creating a new instance of the `Program` class.
    public Program() {
        this.coursesToStates = new HashMap<>();
    }

    /**
     * This is the constructor of the Program class. It initializes the
     * `coursesToStates` field with the given `studentCourses` parameter.
     * 
     * @param studentCourses
     */
    public Program(HashMap<String, CourseStateDto> studentCourses) {
        this.coursesToStates = studentCourses;
    }

    /**
     * This is the constructor of the Program class. It initializes the
     * `coursesToStates` field with the given `courses` parameter.
     * 
     * @param courses
     */
    public Program(List<Course> courses) {
        this.coursesToStates = new HashMap<>();
        for (var course : courses) {
            String key = course.getId();
            coursesToStates.put(key, new CourseStateDto());
        }
    }

    /**
     * Get the state of a course for a student
     * 
     * @param courseId The course ID of the course you want to get the state for.
     * @return The course state for the given course id.
     */
    public CourseStateDto getStudentProgramCourse(String courseId) {
        return coursesToStates.get(courseId);
    }
}
