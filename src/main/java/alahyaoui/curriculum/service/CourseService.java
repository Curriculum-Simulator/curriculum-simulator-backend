package alahyaoui.curriculum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alahyaoui.curriculum.model.Course;
import alahyaoui.curriculum.model.Section;
import alahyaoui.curriculum.repository.CourseRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CourseService {

    @Autowired
    private final CourseRepository courseRepository;

    /**
     * Gets the prices corresponding to the given field
     * according to the filter option given in the parameter.
     * 
     * @param filter the filter option
     * @param field  the field value to look for
     * @return a List of courses
     */
    public List<Course> getCoursesBy(String filter, String field) {
        List<Course> courses;
        if (filter != null && field != null) {
            switch (filter) {
                case "id":
                    courses = courseRepository.findByIdContaining(field.toUpperCase());
                    break;
                case "title":
                    courses = courseRepository.findByTitleContaining(field);
                    break;
                default:
                    courses = (List<Course>) courseRepository.findAll();
            }
        } else {
            courses = (List<Course>) courseRepository.findAll();
        }
        return courses;
    }

    /**
     * Gets all the courses.
     * 
     * @return a list of course
     */
    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    /**
     * Gets all the Gestion courses.
     * 
     * @return a list of course
     */
    public List<Course> getGestionCourses() {
        List<Course> courses = courseRepository.findBySection(Section.COMMUN);
        courses.addAll(courseRepository.findBySection(Section.GESTION));

        return courses;
    }

    /**
     * Gets all the Reseau courses.
     * 
     * @return a list of course
     */
    public List<Course> getReseauCourses() {
        List<Course> courses = courseRepository.findBySection(Section.COMMUN);
        courses.addAll(courseRepository.findBySection(Section.RESEAU));
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIELLE_RESEAU));

        return courses;
    }

    /**
     * Gets all the Industrielle courses.
     * 
     * @return a list of course
     */
    public List<Course> getIndustrielleCourses() {
        List<Course> courses = courseRepository.findBySection(Section.COMMUN);
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIELLE));
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIELLE_RESEAU));

        return courses;
    }
}
