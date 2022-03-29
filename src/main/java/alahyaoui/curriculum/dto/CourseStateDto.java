package alahyaoui.curriculum.dto;

import lombok.Data;

/**
 * A course state contains a boolean value indicating whether a course has been passed or not, and a boolean
 * value indicating whether a course is accessible or not
 */
@Data
public class CourseStateDto {

    private boolean isPassed;
    private boolean isAccessible;

}
