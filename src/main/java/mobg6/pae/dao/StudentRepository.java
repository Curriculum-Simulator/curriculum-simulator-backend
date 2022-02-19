package mobg6.pae.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mobg6.pae.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
