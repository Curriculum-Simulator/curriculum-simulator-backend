package alahyaoui.curriculum.dto;
import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.model.Section;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CourseDto {

    public CourseDto(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.quarter = course.getQuarter();
        this.credits = course.getCredits();
        this.section = course.getSection();
        this.hours = course.getHours();
        this.isPassed = false;
        this.isAccessible = false;
    }

    private final String id;

    private final String title;

    private final int quarter;

    private final int credits;

    private final Section section;

    private final int hours;
    
    private boolean isPassed;

    private boolean isAccessible;
}
