package webg6.pae.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotBlank
    private String title;

    @NotNull
    private int credits;

    @NotNull
    private Section section;

    @NotNull
    private int hours;
    // private List<Student> students;

}
