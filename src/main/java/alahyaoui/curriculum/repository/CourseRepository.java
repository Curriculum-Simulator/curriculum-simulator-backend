package alahyaoui.curriculum.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.model.Section;

@Repository
public interface CourseRepository extends CrudRepository<Course, String> {

    List<Course> findAllByOrderByQuarterAscIdAsc();

    List<Course> findByIdContaining(String id);

    List<Course> findByTitleContaining(String title);

    List<Course> findBySection(Section section);

}
