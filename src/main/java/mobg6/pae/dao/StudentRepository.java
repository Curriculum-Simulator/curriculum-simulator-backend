package mobg6.pae.dao;

import org.springframework.data.repository.CrudRepository;
import mobg6.pae.models.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
