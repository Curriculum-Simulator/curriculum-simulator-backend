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

    @GetMapping("/course")
    public String index() {
        return "course";
    }
}
