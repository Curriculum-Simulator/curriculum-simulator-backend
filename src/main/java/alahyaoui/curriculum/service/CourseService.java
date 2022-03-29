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
     * Get a list of courses based on the filter and field.
     * 
     * @param filter The filter to use. Can be "id" or "title".
     * @param field  The field to filter by.
     * @return A list of courses.
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
     * Get all the courses in the database
     * 
     * @return A list of all the courses from the database.
     */
    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAllByOrderByQuarterAscIdAsc();
    }

    /**
     * Get a course by its id.
     * 
     * @param id The id of the course we want to retrieve.
     * @return a course or throw an error if not found.
     */
    public Course getCourseById(String id) {
        return courseRepository.findById(id).orElseThrow();
    }

    /**
     * Get the courses for a given section
     * 
     * @param section The section of the course.
     * @return A list of courses.
     */
    public List<Course> getSectionCourses(Section section) {
        List<Course> courses;

        switch (section) {
            case MANAGEMENT:
                courses = getManagementCourses();
                break;
            case INDUSTRIAL:
                courses = getIndustrialCourses();
                break;
            case NETWORK:
                courses = getNetworkCourses();
                break;
            default:
                throw new IllegalArgumentException("Section must be either MANAGEMENT, INDUSTRIAL and NETWORK");
        }

        return courses;
    }

    /**
     * Get all the courses from the database that are in the COMMON and GESTION
     * sections.
     * 
     * @return A list of courses.
     */
    private List<Course> getManagementCourses() {
        List<Course> courses = courseRepository.findBySection(Section.COMMON);
        courses.addAll(courseRepository.findBySection(Section.MANAGEMENT));

        return courses;
    }

    private List<Course> getNetworkCourses() {
        List<Course> courses = courseRepository.findBySection(Section.COMMON);
        courses.addAll(courseRepository.findBySection(Section.NETWORK));
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIAL_NETWORK));

        return courses;
    }

    /**
     * It returns a list of all the courses in the database that are in the COMMON,
     * INDUSTRIAL and INDUSTRIAL_NETWORK sections.
     * 
     * @return A list of courses.
     */
    private List<Course> getIndustrialCourses() {
        List<Course> courses = courseRepository.findBySection(Section.COMMON);
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIAL));
        courses.addAll(courseRepository.findBySection(Section.INDUSTRIAL_NETWORK));

        return courses;
    }
}
