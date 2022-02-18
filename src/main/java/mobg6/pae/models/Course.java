package mobg6.pae.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    private String title;
    private int credits;
    private List<Student> students;
}
