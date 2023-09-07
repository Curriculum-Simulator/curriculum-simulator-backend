package alahyaoui.curriculum.business;

import java.util.HashMap;
import alahyaoui.curriculum.dto.CourseStateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Program_Program_c46339f534_Test {

    private Program program;
    private HashMap<String, CourseStateDto> studentCourses;

    @BeforeEach
    public void setup() {
        studentCourses = new HashMap<>();
        studentCourses.put("Math", new CourseStateDto());
        studentCourses.put("English", new CourseStateDto());
        program = new Program();
    }

    @Test
    public void testProgramConstructor() {
        assertEquals(studentCourses, program.getCoursesToStates());
    }

    @Test
    public void testProgramConstructorWithEmptyMap() {
        program = new Program();
        assertTrue(program.getCoursesToStates().isEmpty());
    }
}
