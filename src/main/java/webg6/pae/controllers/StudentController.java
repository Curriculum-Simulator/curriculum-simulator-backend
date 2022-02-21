package webg6.pae.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import webg6.pae.dao.StudentRepository;

@Controller
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private final StudentRepository studentRepository;
    
    @GetMapping("/student")
    public String index(Model model) {
        var students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "student";
    }
}
