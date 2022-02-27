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
        Section section = Section.GESTION;
        List<Course> courses = courseService.getSectionCourses(section);
        // Add attributes to model
        model.addAttribute("courses", courses);
        model.addAttribute("section", section);
        return "course_result";
    }

    @GetMapping("/courses/reseau")
    public String getReseauCourses(Model model) {
        Section section = Section.RESEAU;
        List<Course> courses = courseService.getSectionCourses(section);
        // Add attributes to model
        model.addAttribute("courses", courses);
        model.addAttribute("section", section);
        return "course_result";
    }

    @GetMapping("/courses/industrielle")
    public String getIndustrielleCourses(Model model) {
        Section section = Section.INDUSTRIELLE;
        List<Course> courses = courseService.getSectionCourses(section);
        // Add attributes to model
        model.addAttribute("courses", courses);
        model.addAttribute("section", section);
        return "course_result";
    }

}
