package alahyaoui.curriculum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.model.Section;
import alahyaoui.curriculum.service.CourseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CourseRestController {

    @Autowired
    private final CourseService courseService;

    /**
     * Get all courses from the database and add them to the model
     * 
     * @param model  The Model object is a Map that is used to pass data between the
     *               controller and the
     *               view.
     * @param filter The filter to use. This is a string that is used to filter the
     *               results.
     * @param field  The field to filter by.
     * @return The view name "course_search"
     */
    @GetMapping("/api/courses")
    public ResponseEntity<List<Course>> getCourses(@RequestParam(required = false) String filter, @RequestParam(required = false) String field) {
        return new ResponseEntity<>(courseService.getCoursesBy(filter, field), HttpStatus.OK);
    }

    /**
     * Get all courses from the database and add them to the model
     * 
     * @param model The Model object is a Map that is used to pass data between the
     *              controller and the
     *              view.
     * @return The view name course_result.
     */
    @GetMapping("/api/courses/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    /**
     * Get all courses of a given section
     * 
     * @param model       The Model object is a Map that is used to pass data
     *                    between the controller and the
     *                    view.
     * @param userSection The section the user is looking for.
     * @return The view name "course_result"
     */
    @GetMapping("/api/courses/{section}")
    public ResponseEntity<List<Course>> getSectionCourses(@PathVariable Section section) { 
        return new ResponseEntity<>(courseService.getSectionCourses(section), HttpStatus.OK);
    }
}
