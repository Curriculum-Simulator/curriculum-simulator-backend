package webg6.pae.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

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
    private String name;
    private String fname;
    private Gender gender;
    private Section section;
    //private List<Course> courses;
}
