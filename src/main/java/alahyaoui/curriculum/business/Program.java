package alahyaoui.curriculum.business;

import java.util.HashMap;
import java.util.List;

import alahyaoui.curriculum.dto.CourseStateDto;
import alahyaoui.curriculum.model.Course;
import lombok.Value;

@Value
public class Program {

    private final HashMap<String, CourseStateDto> coursesToStates;

    public Program() {
        this.coursesToStates = new HashMap<>();
    }

    public Program(HashMap<String, CourseStateDto> studentCourses) {
        this.coursesToStates = studentCourses;
    }

    public Program(List<Course> courses) {
        this.coursesToStates = new HashMap<>();
        for (var course : courses) {
            String key = course.getId();
            coursesToStates.put(key, new CourseStateDto());
        }
    }

    public CourseStateDto getStudentProgramCourse(String courseId) {
        return coursesToStates.get(courseId);
    }
}
