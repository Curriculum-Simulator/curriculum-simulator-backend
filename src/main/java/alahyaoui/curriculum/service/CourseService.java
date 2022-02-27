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
                    courses = (List<Course>) courseRepository.findAllByOrderByQuarterAscIdAsc();
            }
        } else {
            courses = (List<Course>) courseRepository.findAllByOrderByQuarterAscIdAsc();
        }
        return courses;
    }

    /**
     * Gets all the courses.
     * 
     * @return a list of course
     */
    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAllByOrderByQuarterAscIdAsc();
    }

        /**
     * Gets the course corresponding to the given id.
     * 
     * @return a course
     */
    public  Course getCourseById(String id) {
        return courseRepository.findById(id).orElseThrow();
    }

    /**
     * Gets all the section courses.
     * 
     * @return a list of course
     */
    public List<Course> getSectionCourses(Section section) {
        List<Course> courses;

        switch (section) {
            case GESTION:
                courses = getGestionCourses();
                break;
            case INDUSTRIELLE:
                courses = getIndustrielleCourses();
                break;
            case RESEAU:
                courses = getReseauCourses();
                break;
            default:
                throw new IllegalArgumentException("Section must be either GESTION, INDUSTRIELLE and RESEAU");
        }

        return courses;
    }

    /**
     * Gets all the Gestion courses.
     * 
     * @return a list of course
     */
    private List<Course> getGestionCourses() {
        List<Course> courses = courseRepository.findBySection(Section.COMMUN);
        courses.addAll(courseRepository.findBySection(Section.GESTION));

        return courses;
    }

    /**
     * Gets all the Reseau courses.
     * 
     * @return a list of course
     */
    private List<Course> getReseauCourses() {
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
    private List<Course> getIndustrielleCourses() {
        List<Course> courses = courseRepository.findBySection(Section.COMMUN);
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIELLE));
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIELLE_RESEAU));

        return courses;
    }
}
