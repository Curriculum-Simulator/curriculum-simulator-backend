package mobg6.pae.seeders;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import mobg6.pae.dao.CourseRepository;
import mobg6.pae.models.Course;
import mobg6.pae.models.Section;

@Component
@RequiredArgsConstructor
public class CourseSeeder implements CommandLineRunner {

    private static final String COMMA_DELIMITER = ",";

    @Autowired
    private final CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        seed();
    }

    private void seed() throws CsvValidationException, NumberFormatException, FileNotFoundException, IOException {
        seedSection(Section.COMMUN);
        seedSection(Section.GESTION);
        seedSection(Section.INDUSTRIELLE);
        seedSection(Section.RESEAU);
        seedSection(Section.INDUSTRIELLE_RESEAU);
    }

    private void seedSection(Section section)
            throws FileNotFoundException, IOException, CsvValidationException, NumberFormatException {
        String path = "static/data/cours_" + section.toString().toLowerCase() + ".csv";
        var fileReader = new InputStreamReader(new ClassPathResource(path).getInputStream());

        try (CSVReader reader = new CSVReader(fileReader)) {
            String[] values;
            while ((values = reader.readNext()) != null) {
                Course course = new Course();
                course.setId(values[1]);
                course.setTitle(values[2]);
                course.setCredits(Integer.parseInt(values[3]));
                course.setSection(section);
                course.setHours(Integer.parseInt(values[4]));
                // course.setStudents(new ArrayList<>());
                courseRepository.save(course);
            }
        }
    }
}
