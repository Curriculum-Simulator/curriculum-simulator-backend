package alahyaoui.curriculum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import alahyaoui.curriculum.business.Program;
import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.model.Section;
import alahyaoui.curriculum.service.ProgramService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProgramController {

    @Autowired
    private final ProgramService programService;

    @GetMapping("/program")
    public String getProgramView(Model model, @RequestParam(required = false) Section section) throws Exception {
        Program program = programService.getStudentProgram(section);
        model.addAttribute("program", program);
        return "program";
    }

    /**
     * This method is used to update the student program and get the new computed
     * student program.
     * 
     * @param model   The Model object is a Map that is used to pass information
     *                between the controller
     *                and the view.
     * @param program The program to be updated.
     * @return The program page is being returned.
     */
    @PostMapping("/program")
    public String submitProgram(Model model, Program program) {
        programService.updateProgram(program);
        List<Course> courses = programService.getAnnualStudentProgram(program);
        model.addAttribute("pae", courses);
        return "program";
    }
}
