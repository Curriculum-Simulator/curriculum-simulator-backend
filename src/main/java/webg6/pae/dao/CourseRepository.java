package webg6.pae.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import webg6.pae.models.Course;
import webg6.pae.models.Section;

@Repository
public interface CourseRepository extends CrudRepository<Course, String> {

    List<Course> findBySection(Section section);
}
