package alahyaoui.curriculum.exception;

public class CourseNotFoundException extends RuntimeException {
    
    public CourseNotFoundException(String id) {
        super("Could not find course " + id);
    }
}
