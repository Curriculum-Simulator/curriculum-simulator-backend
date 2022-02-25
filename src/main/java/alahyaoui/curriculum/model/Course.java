package alahyaoui.curriculum.models;

import javax.persistence.Entity;
import javax.persistence.Id;
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
    
    @Id
    private String id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @Positive(message = "Credits must be positive")
    private int credits;

    @NotNull(message = "Section is mandatory")
    private Section section;

    @Positive(message = "Hours must be positive")
    private int hours;
}
