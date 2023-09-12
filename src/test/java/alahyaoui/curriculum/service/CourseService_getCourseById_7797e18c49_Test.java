package alahyaoui.curriculum.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.repository.CourseRepository;

public class CourseService_getCourseById_7797e18c49_Test {

    @Mock
    private CourseRepository courseRepository;

    private CourseService courseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        courseService = new CourseService(courseRepository);
    }

    @Test
    public void testGetCourseById_success() {
        Course course = new Course();
        course.setId("1");
        course.setName("Java Programming");
        when(courseRepository.findById("1")).thenReturn(Optional.of(course));
        
        Course result = courseService.getCourseById("1");
        assertEquals(course, result);
    }

    @Test
    public void testGetCourseById_notFound() {
        when(courseRepository.findById("2")).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> courseService.getCourseById("2"));
    }
}
