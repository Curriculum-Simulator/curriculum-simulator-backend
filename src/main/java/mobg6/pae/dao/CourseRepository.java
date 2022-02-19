package mobg6.pae.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mobg6.pae.models.Course;
import mobg6.pae.models.Section;

@Repository
public interface CourseRepository extends CrudRepository<Course, String> {

    List<Course> findBySection(Section section);
}
