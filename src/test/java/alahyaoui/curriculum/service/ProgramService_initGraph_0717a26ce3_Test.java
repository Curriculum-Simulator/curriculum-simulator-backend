package alahyaoui.curriculum.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import alahyaoui.curriculum.business.CourseGraph;
import alahyaoui.curriculum.business.CourseNode;
import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.service.CourseService;

public class ProgramService_initGraph_0717a26ce3_Test {

    @InjectMocks
    private ProgramService programService;

    @Mock
    private CourseService courseService;

    @Mock
    private CourseGraph courseGraph;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInitGraph_whenCoursesArePresent() {
        Course course1 = new Course();
        Course course2 = new Course();
        List<Course> courses = Arrays.asList(course1, course2);

        when(courseService.getAllCourses()).thenReturn(courses);

        programService.initGraph();

        courses.forEach(course -> {
            verify(courseGraph, times(1)).addNode(any(CourseNode.class));
        });
    }

    @Test
    public void testInitGraph_whenNoCoursesArePresent() {
        List<Course> courses = Arrays.asList();

        when(courseService.getAllCourses()).thenReturn(courses);

        programService.initGraph();

        verify(courseGraph, times(0)).addNode(any(CourseNode.class));
    }
}
