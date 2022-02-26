package alahyaoui.curriculum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/courses")
    public String getCourses(Model model, @RequestParam(required = false) String filter, @RequestParam(required = false) String field) {
        List<Course> courses = courseService.getCoursesBy(filter, field);
        model.addAttribute("courses", courses);

        return "course_search";
    }

    @GetMapping("/courses/all")
    public String getAllCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "course_result";
    }

    @GetMapping("/courses/gestion")
    public String getGestionCourses(Model model) {
        List<Course> courses = courseService.getGestionCourses();

        // Add attributes to model
        model.addAttribute("courses", courses);
        model.addAttribute("section", Section.GESTION);
        return "course_result";
    }

    @GetMapping("/courses/reseau")
    public String getReseauCourses(Model model) {
        List<Course> courses = courseService.getReseauCourses();

        // Add attributes to model
        model.addAttribute("courses", courses);
        model.addAttribute("section", Section.RESEAU);
        return "course_result";
    }

    @GetMapping("/courses/industrielle")
    public String getIndustrielleCourses(Model model) {
        List<Course> courses = courseService.getIndustrielleCourses();
        // Add attributes to model
        model.addAttribute("courses", courses);
        model.addAttribute("section", Section.INDUSTRIELLE);
        return "course_result";
    }

}
