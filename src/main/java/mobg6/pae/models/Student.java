package mobg6.pae.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private Gender gender;
    private Section section;
    private List<Course> courses;
}
