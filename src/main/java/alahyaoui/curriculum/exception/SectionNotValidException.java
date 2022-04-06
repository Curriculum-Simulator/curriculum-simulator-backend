package alahyaoui.curriculum.exception;

import alahyaoui.curriculum.model.Section;

public class SectionNotValidException extends RuntimeException {
    
    public SectionNotValidException(Section section) {
        super("Section must be either MANAGEMENT, INDUSTRIAL and NETWORK value received: " + section);
    }
}
