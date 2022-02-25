package alahyaoui.curriculum.controller;

import org.springframework.beans.factory.annotation.Autowired;

import alahyaoui.curriculum.service.ProgramService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProgramController {

    @Autowired
    private final ProgramService programService;
    
}
