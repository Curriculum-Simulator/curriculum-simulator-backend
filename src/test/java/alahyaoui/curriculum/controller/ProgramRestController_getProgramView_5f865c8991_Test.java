package alahyaoui.curriculum.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import alahyaoui.curriculum.dto.CourseDto;
import alahyaoui.curriculum.model.Section;
import alahyaoui.curriculum.service.ProgramService;

public class ProgramRestController_getProgramView_5f865c8991_Test {

    @InjectMocks
    ProgramRestController programRestController;

    @Mock
    ProgramService programService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProgramView_Success() throws Exception {
        Section section = new Section(); // TODO: Set appropriate values
        List<CourseDto> courseList = new ArrayList<>(); // TODO: Add some mock courses
        when(programService.getProgram(section)).thenReturn(courseList);

        ResponseEntity<List<CourseDto>> response = programRestController.getProgramView(section);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseList, response.getBody());
    }

    @Test
    public void testGetProgramView_Failure() throws Exception {
        Section section = new Section(); // TODO: Set appropriate values
        when(programService.getProgram(section)).thenThrow(new Exception("Service failure"));

        try {
            programRestController.getProgramView(section);
        } catch (Exception e) {
            assertEquals("Service failure", e.getMessage());
        }
    }
}
