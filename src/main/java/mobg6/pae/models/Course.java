package mobg6.pae.models;

import java.util.List;
import javax.persistence.Entity;

@Entity
public class Course {
    private String id;
    private String title;
    private int credits;
    private List<Student> students;
}
