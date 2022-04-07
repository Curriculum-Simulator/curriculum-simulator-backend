package alahyaoui.curriculum.exception;

public class SectionNotFoundException extends RuntimeException {

    public SectionNotFoundException(String section) {
      super("Could not find section " + section);
    }
}
