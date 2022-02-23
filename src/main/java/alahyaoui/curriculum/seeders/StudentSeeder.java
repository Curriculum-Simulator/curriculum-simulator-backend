package webg6.pae.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import webg6.pae.dao.StudentRepository;
import webg6.pae.models.Gender;
import webg6.pae.models.Section;
import webg6.pae.models.Student;

@Component
@RequiredArgsConstructor
public class StudentSeeder implements CommandLineRunner {

    @Autowired
    private final StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        seed();
    }

    private void seed() {
        seedStudent1();
        seedStudent2();
        seedStudent3();
        seedStudent4();
        seedStudent5();
    }

    private void seedStudent1() {
        Student student = new Student();
        student.setId(54895);
        student.setName("Lahyaoui");
        student.setFname("Ayoub");
        student.setGender(Gender.MALE);
        student.setSection(Section.GESTION);
        // student.setCourses(new ArrayList<>());
        studentRepository.save(student);
    }

    private void seedStudent2() {
        Student student = new Student();
        student.setId(54018);
        student.setName("Routier");
        student.setFname("Basile");
        student.setGender(Gender.MALE);
        student.setSection(Section.GESTION);
        // student.setCourses(new ArrayList<>());
        studentRepository.save(student);
    }

    private void seedStudent3() {
        Student student = new Student();
        student.setId(54356);
        student.setName("Kanan");
        student.setFname("Hosam");
        student.setGender(Gender.MALE);
        student.setSection(Section.GESTION);
        // student.setCourses(new ArrayList<>());
        studentRepository.save(student);
    }

    private void seedStudent4() {
        Student student = new Student();
        student.setId(54247);
        student.setName("Bendaimi");
        student.setFname("Zakaria");
        student.setGender(Gender.MALE);
        student.setSection(Section.GESTION);
        // student.setCourses(new ArrayList<>());
        studentRepository.save(student);
    }

    private void seedStudent5() {
        Student student = new Student();
        student.setId(54985);
        student.setName("Bigham");
        student.setFname("Amine-Ayoub");
        student.setGender(Gender.MALE);
        student.setSection(Section.GESTION);
        // student.setCourses(new ArrayList<>());
        studentRepository.save(student);
    }

}
