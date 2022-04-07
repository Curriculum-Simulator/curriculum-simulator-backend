package alahyaoui.curriculum.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import alahyaoui.curriculum.exception.SectionNotFoundException;
import alahyaoui.curriculum.exception.SectionNotValidException;

@ControllerAdvice
public class SectionExceptionController {

    @ResponseBody
    @ExceptionHandler(SectionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String sectionNotFoundHandler(SectionNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SectionNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String sectionNotValidHandler(SectionNotValidException ex) {
        return ex.getMessage();
    }
}
