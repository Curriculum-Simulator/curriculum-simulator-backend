package alahyaoui.curriculum.service;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.repository.CourseRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CourseService_getAllCourses_9dc287d8de_Test {
    
    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    private List<Course> courseList;

    @BeforeEach
    public void setup() {
        courseList = Arrays.asList(
            new Course(), new Course(), new Course()
        );
    }

    @Test
    public void testGetAllCourses_success() {
        when(courseRepository.findAllByOrderByQuarterAscIdAsc()).thenReturn(courseList);
        List<Course> result = courseService.getAllCourses();
        assertEquals(courseList, result);
    }

    @Test
    public void testGetAllCourses_empty() {
        when(courseRepository.findAllByOrderByQuarterAscIdAsc()).thenReturn(Arrays.asList());
        List<Course> result = courseService.getAllCourses();
        assertEquals(0, result.size());
    }
}
