package mobg6.pae.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import mobg6.pae.dao.CourseRepository;
import mobg6.pae.models.Section;

@Controller
@RequiredArgsConstructor
public class CourseController {

    @Autowired 
    private final CourseRepository courseRepository;

    @GetMapping("/course")
    public String index() {
        return "course";
    }

    @GetMapping("/course/all")
    public String getAllCourses(Model model) {
        var courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "course";
    }

    @GetMapping("/course/gestion")
    public String getGestionCourses(Model model) {
        var courses = courseRepository.findBySection(Section.COMMUN);
        courses.addAll(courseRepository.findBySection(Section.GESTION));

        //Add attributes to model
        model.addAttribute("courses", courses);
        model.addAttribute("section", Section.GESTION);
        return "course";
    }

    @GetMapping("/course/reseau")
    public String getReseauCourses(Model model) {
        var courses = courseRepository.findBySection(Section.COMMUN);
        courses.addAll(courseRepository.findBySection(Section.RESEAU));
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIELLE_RESEAU));

        //Add attributes to model
        model.addAttribute("courses", courses);
        model.addAttribute("section", Section.RESEAU);
        return "course";
    }

    @GetMapping("/course/industrielle")
    public String getIndustrielleCourses(Model model) {
        var courses = courseRepository.findBySection(Section.COMMUN);
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIELLE));
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIELLE_RESEAU));
        
        //Add attributes to model
        model.addAttribute("courses", courses);
        model.addAttribute("section", Section.INDUSTRIELLE);
        return "course";
    }
    
}
