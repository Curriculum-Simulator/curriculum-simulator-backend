package alahyaoui.curriculum.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private static final int MIN_QUARTER = 1;
    private static final int MAX_QUARTER = 6;

    @Id
    private String id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @Min(value = MIN_QUARTER, message = "Quarter must be greater than or equal to " + MIN_QUARTER)
    @Max(value = MAX_QUARTER, message = "Quarter must be smaller than or equal to " + MAX_QUARTER)
    private int quarter;

    @Positive(message = "Credits must be positive")
    private int credits;

    @NotNull(message = "Section is mandatory")
    private Section section;

    @Positive(message = "Hours must be positive")
    private int hours;
}
