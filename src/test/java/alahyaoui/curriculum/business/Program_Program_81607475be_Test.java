package alahyaoui.curriculum.business;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import alahyaoui.curriculum.dto.CourseStateDto;
import alahyaoui.curriculum.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Program_Program_81607475be_Test {

    private List<Course> courses;
    private Program program;

    @BeforeEach
    public void setUp() {
        Course course1 = Mockito.mock(Course.class);
        Course course2 = Mockito.mock(Course.class);

        Mockito.when(course1.getId()).thenReturn("1");
        Mockito.when(course2.getId()).thenReturn("2");

        courses = Arrays.asList(course1, course2);
        program = new Program();
    }

    @Test
    public void testProgram_constructor() {
        HashMap<String, CourseStateDto> coursesToStates = program.getCoursesToStates();
        assertEquals(2, coursesToStates.size());

        assertTrue(coursesToStates.containsKey("1"));
        assertTrue(coursesToStates.containsKey("2"));
    }

    @Test
    public void testProgram_constructor_emptyList() {
        Program emptyProgram = new Program();
        assertTrue(emptyProgram.getCoursesToStates().isEmpty());
    }
}
