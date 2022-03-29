package alahyaoui.curriculum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.model.Section;
import alahyaoui.curriculum.service.CourseService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CourseController {

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
    @GetMapping("/courses")
    public String getCourses(Model model, @RequestParam(required = false) String filter,
            @RequestParam(required = false) String field) {
        List<Course> courses = courseService.getCoursesBy(filter, field);

        model.addAttribute("courses", courses);
        return "course_search";
    }

    /**
     * Get all courses from the database and add them to the model
     * 
     * @param model The Model object is a Map that is used to pass data between the
     *              controller and the
     *              view.
     * @return The view name course_result.
     */
    @GetMapping("/courses/all")
    public String getAllCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();

        model.addAttribute("courses", courses);
        return "course_result";
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
    @GetMapping("/courses/{userSection}")
    public String getSectionCourses(Model model, @PathVariable String userSection) {
        Section section;
        switch (userSection) {
            case "management" -> section = Section.MANAGEMENT;
            case "network" -> section = Section.NETWORK;
            case "industrial" -> section = Section.INDUSTRIAL;
            default -> section = Section.MANAGEMENT;
        }
        List<Course> courses = courseService.getSectionCourses(section);

        model.addAttribute("courses", courses);
        model.addAttribute("section", section);
        return "course_result";
    }
}
