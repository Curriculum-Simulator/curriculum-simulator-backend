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
public class Student {
    @Id
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String fname;

    @NotNull
    private Gender gender;

    @NotNull
    private Section section;
    //private List<Course> courses;
}
