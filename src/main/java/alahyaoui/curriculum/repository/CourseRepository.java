package alahyaoui.curriculum.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.model.Section;

// A repository interface that extends the CrudRepository interface.
@Repository
public interface CourseRepository extends CrudRepository<Course, String> {

    /**
     * Find all the courses in the database, order them by quarter in ascending order, and then by id
     * in ascending order
     * 
     * @return A list of Course objects.
     */
    List<Course> findAllByOrderByQuarterAscIdAsc();

    /**
     * Find all courses whose id contains the given id
     * 
     * @param id The id of the course you want to find.
     * @return A List of Course objects
     */
    List<Course> findByIdContaining(String id);

    /**
     * Find all courses with a title containing the given string.
     * 
     * @param title The title of the course.
     * @return A List of Course objects
     */
    List<Course> findByTitleContaining(String title);

    /**
     * Find all the courses that are offered in a given section
     * 
     * @param section The section to search for.
     * @return A list of Course objects.
     */
    List<Course> findBySection(Section section);
}
