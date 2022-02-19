package mobg6.pae.seeders;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.context.event.EventListener;

import mobg6.pae.dao.StudentRepository;
import mobg6.pae.models.Gender;
import mobg6.pae.models.Section;
import mobg6.pae.models.Student;

public class StudentSeeder {

    private StudentRepository studentRepository;

    @EventListener
    public void seed() throws FileNotFoundException, IOException {
        seedStudent1();
        seedStudent2();
        seedStudent3();
        seedStudent4();
        seedStudent5();
    }

    private void seedStudent1(){
        Student student = new Student();
        student.setId(54895);
        student.setName("Lahyaoui");
        student.setFname("Ayoub");
        student.setGender(Gender.MALE);
        student.setSection(Section.GESTION);
        student.setCourses(new ArrayList<>());
        studentRepository.save(student);
    }

    private void seedStudent2(){
        Student student = new Student();
        student.setId(54018);
        student.setName("Routier");
        student.setFname("Basile");
        student.setGender(Gender.MALE);
        student.setSection(Section.GESTION);
        student.setCourses(new ArrayList<>());
        studentRepository.save(student);
    }

    private void seedStudent3(){
        Student student = new Student();
        student.setId(54356);
        student.setName("Kanan");
        student.setFname("Hosam");
        student.setGender(Gender.MALE);
        student.setSection(Section.GESTION);
        student.setCourses(new ArrayList<>());
        studentRepository.save(student);
    }

    private void seedStudent4(){
        Student student = new Student();
        student.setId(54247);
        student.setName("Bendaimi");
        student.setFname("Zakaria");
        student.setGender(Gender.MALE);
        student.setSection(Section.GESTION);
        student.setCourses(new ArrayList<>());
        studentRepository.save(student);
    }

    private void seedStudent5(){
        Student student = new Student();
        student.setId(54985);
        student.setName("Bigham");
        student.setFname("Amine-Ayoub");
        student.setGender(Gender.MALE);
        student.setSection(Section.GESTION);
        student.setCourses(new ArrayList<>());
        studentRepository.save(student);
    }
    
}
