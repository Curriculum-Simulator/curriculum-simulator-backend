package alahyaoui.curriculum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alahyaoui.curriculum.business.Program;
import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.model.Section;
import alahyaoui.curriculum.service.ProgramService;
import groovyjarjarpicocli.CommandLine.Model;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProgramRestController {

    @Autowired
    private final ProgramService programService;

    @GetMapping("/api/program")
    public Program getProgramView(Model model, @RequestParam(required = false) Section section) throws Exception {
        return programService.getStudentProgram(section);
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
    @PostMapping("/api/program")
    public List<Course> submitProgram(Model model, Program program) {
        // @TOFIX Update two times in case of not properly treated courses
        programService.updateProgram(program);
        programService.updateProgram(program);
        return programService.getAnnualStudentProgram(program);
    }
}
