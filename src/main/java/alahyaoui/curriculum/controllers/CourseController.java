package webg6.pae.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import webg6.pae.dao.CourseRepository;
import webg6.pae.models.Course;
import webg6.pae.models.Section;

@Controller
@RequiredArgsConstructor
public class CourseController {

    @Autowired
    private final CourseRepository courseRepository;

    @GetMapping("/course")
    public String index(Model model, @RequestParam(required = false) String filter, @RequestParam(required = false) String field) {
        List<Course> courses;
        if (filter != null && field != null) {
            switch (filter) {
                case "id":
                    courses = courseRepository.findByIdContaining(field.toUpperCase());
                    break;
                case "title":
                    courses = courseRepository.findByTitleContaining(field);
                    break;
                default:
                    courses = (List<Course>) courseRepository.findAll();
            }
        }else{
            courses = (List<Course>) courseRepository.findAll();
        }
        model.addAttribute("courses", courses);

        return "course_search";
    }

    @GetMapping("/course/all")
    public String getAllCourses(Model model) {
        var courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "course_result";
    }

    @GetMapping("/course/gestion")
    public String getGestionCourses(Model model) {
        var courses = courseRepository.findBySection(Section.COMMUN);
        courses.addAll(courseRepository.findBySection(Section.GESTION));

        // Add attributes to model
        model.addAttribute("courses", courses);
        model.addAttribute("section", Section.GESTION);
        return "course_result";
    }

    @GetMapping("/course/reseau")
    public String getReseauCourses(Model model) {
        var courses = courseRepository.findBySection(Section.COMMUN);
        courses.addAll(courseRepository.findBySection(Section.RESEAU));
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIELLE_RESEAU));

        // Add attributes to model
        model.addAttribute("courses", courses);
        model.addAttribute("section", Section.RESEAU);
        return "course_result";
    }

    @GetMapping("/course/industrielle")
    public String getIndustrielleCourses(Model model) {
        var courses = courseRepository.findBySection(Section.COMMUN);
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIELLE));
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIELLE_RESEAU));

        // Add attributes to model
        model.addAttribute("courses", courses);
        model.addAttribute("section", Section.INDUSTRIELLE);
        return "course_result";
    }

}
