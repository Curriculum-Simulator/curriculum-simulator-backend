package webg6.pae.business;

import java.util.ArrayList;
import java.util.List;

import lombok.Value;

@Value
public class CourseNode {

    private final String id;
    private final List<CourseNode> prerequisites;
    private final List<CourseNode> corequisites;

    public CourseNode(String id){
        this.id = id;
        prerequisites = new ArrayList<>();
        corequisites = new ArrayList<>();
    }

    public void addPrerequisite(CourseNode prerequisite){
        prerequisites.add(prerequisite);
    }

    public void addCorequisite(CourseNode corequisite){
        corequisites.add(corequisite);
    }
}
