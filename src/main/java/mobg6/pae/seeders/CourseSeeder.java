package mobg6.pae.seeders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.context.event.EventListener;

import mobg6.pae.dao.CourseRepository;
import mobg6.pae.models.Course;
import mobg6.pae.models.Section;

public class CourseSeeder {

    private static final String COMMA_DELIMITER = ",";

    private CourseRepository courseRepository;


    @EventListener
    public void seed() throws FileNotFoundException, IOException {
        seedSection(Section.COMMUN);
        seedSection(Section.GESTION);
        seedSection(Section.INDUSTRIELLE);
        seedSection(Section.RESEAU);
        seedSection(Section.INDUSTRIELLE_RESEAU);
    }

    private void seedSection(Section section) throws FileNotFoundException, IOException {

        try (BufferedReader br = new BufferedReader(new FileReader("book.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                Course course = new Course();
                course.setId(values[1]); 
                course.setTitle(values[2]); 
                course.setCredits(Integer.parseInt(values[3]));
                course.setSection(section);
                course.setHours(Integer.parseInt(values[4]));
                course.setStudents(new ArrayList<>()); 
                courseRepository.save(course);
            }
        }
    }
}
