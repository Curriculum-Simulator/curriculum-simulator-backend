package alahyaoui.curriculum.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Value;

@Value
public class CourseNode {

    private final String id;
    private final Set<CourseNode> prerequisites;
    private final Set<CourseNode> corequisites;

    public CourseNode(String id){
        this.id = id;
        prerequisites = new HashSet<>();
        corequisites = new HashSet<>();
    }

    public void addPrerequisite(CourseNode prerequisite){
        prerequisites.add(prerequisite);
    }

    public void addCorequisite(CourseNode corequisite){
        corequisites.add(corequisite);
    }
}
