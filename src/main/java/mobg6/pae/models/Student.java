package mobg6.pae.models;

import java.util.List;
import javax.persistence.Entity;

@Entity
public class Student {
    private int id;
    private String name;
    private Gender gender;
    private Section section;
    private List<Course> courses;
}
