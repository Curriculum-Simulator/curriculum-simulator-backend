package mobg6.pae.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Course {
    @Id
    private String id;
    private String title;
    private int credits;
    private Section section;
    private int hours;
    private List<Student> students;

}
