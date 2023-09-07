package alahyaoui.curriculum.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import alahyaoui.curriculum.dto.CourseDto;
import alahyaoui.curriculum.service.ProgramService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProgramRestController_submitProgram_f4e6ae456a_Test {

    @InjectMocks
    private ProgramRestController controller;

    @Mock
    private ProgramService programService;

    @Test
    public void testSubmitProgram_success() {
        List<CourseDto> program = new ArrayList<>();
        ResponseEntity<List<CourseDto>> response = controller.submitProgram((ArrayList<CourseDto>) program);
        
        verify(programService, times(2)).updateProgram(program);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(program, response.getBody());
    }

    @Test
    public void testSubmitProgram_nullProgram() {
        ResponseEntity<List<CourseDto>> response = controller.submitProgram(null);
        
        verify(programService, times(2)).updateProgram(null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}
