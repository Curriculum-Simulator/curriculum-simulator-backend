package webg6.pae.business;

import java.io.IOException;
import java.io.InputStreamReader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import webg6.pae.dao.CourseRepository;

public class PAE {
    
    private final CourseGraph courseGraph;

    @Autowired
    CourseRepository courseRepository;

    public PAE() throws NumberFormatException, CsvValidationException, IOException{
        courseGraph = new CourseGraph();
        initGraph();
        initPrerequisites();
        initCorequisites();
    }

    public void initGraph(){
        var courses = courseRepository.findAll();
        for(var course : courses){
            CourseNode node = new CourseNode(course.getId());
            courseGraph.addNode(node);
        }
    }

    private void initPrerequisites() throws NumberFormatException, CsvValidationException, IOException {
        String path = "static/data/prerequisites.csv";
        var fileReader = new InputStreamReader(new ClassPathResource(path).getInputStream());

        try (CSVReader reader = new CSVReader(fileReader)) {
            String[] values;
            while ((values = reader.readNext()) != null) {
                CourseNode courseNode = courseGraph.search(values[0]);
                CourseNode prerequisiteNode = courseGraph.search(values[1]);
                courseNode.addPrerequisite(prerequisiteNode);
            }
        }
    }

    private void initCorequisites() throws NumberFormatException, CsvValidationException, IOException {
        String path = "static/data/corequisites.csv";
        var fileReader = new InputStreamReader(new ClassPathResource(path).getInputStream());

        try (CSVReader reader = new CSVReader(fileReader)) {
            String[] values;
            while ((values = reader.readNext()) != null) {
                CourseNode courseNode = courseGraph.search(values[0]);
                CourseNode corequisiteNode = courseGraph.search(values[1]);
                courseNode.addCorequisite(corequisiteNode);
            }
        }
    }
}
