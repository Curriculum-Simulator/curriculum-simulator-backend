package alahyaoui.curriculum.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.service.CourseService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseRestController_getCourses_977b5b1a4a_Test {

    @InjectMocks
    private CourseRestController courseRestController;

    @Mock
    private CourseService courseService;

    private List<Course> courses;

    @BeforeEach
    public void setUp() {
        Course course1 = new Course();
        course1.setName("Course1");
        course1.setField("Field1");

        Course course2 = new Course();
        course2.setName("Course2");
        course2.setField("Field2");

        Course course3 = new Course();
        course3.setName("Course3");
        course3.setField("Field3");

        courses = Arrays.asList(course1, course2, course3);
    }

    @Test
    public void testGetCourses_withFilterAndField() {
        when(courseService.getCoursesBy("filter", "field")).thenReturn(courses);

        ResponseEntity<List<Course>> response = courseRestController.getCourses("filter", "field");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courses, response.getBody());
    }

    @Test
    public void testGetCourses_withoutFilterAndField() {
        when(courseService.getCoursesBy(null, null)).thenReturn(courses);

        ResponseEntity<List<Course>> response = courseRestController.getCourses(null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courses, response.getBody());
    }
}
