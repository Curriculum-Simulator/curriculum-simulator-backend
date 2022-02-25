package alahyaoui.curriculum.business;

import java.util.HashMap;
import alahyaoui.curriculum.dto.ProgramCourseDto;
import lombok.Value;

@Value
public class Program {

    private final HashMap<String, ProgramCourseDto> studentCourses;

    public Program() {
        this.studentCourses = new HashMap<>();
    }

    public Program(HashMap<String, ProgramCourseDto> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public ProgramCourseDto getStudentProgramCourse(String courseId) {
        return studentCourses.get(courseId);
    }
}
