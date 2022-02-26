package alahyaoui.curriculum.business;

import java.util.HashMap;
import alahyaoui.curriculum.dto.CourseStateDto;
import lombok.Value;

@Value
public class Program {

    private final HashMap<String, CourseStateDto> studentCourses;

    public Program() {
        this.studentCourses = new HashMap<>();
    }

    public Program(HashMap<String, CourseStateDto> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public CourseStateDto getStudentProgramCourse(String courseId) {
        return studentCourses.get(courseId);
    }
}
