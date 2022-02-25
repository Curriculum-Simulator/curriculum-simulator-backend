package alahyaoui.curriculum.business;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
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

    public CourseNode searchPrerequisite(String id) {
        for (Iterator<CourseNode> it = prerequisites.iterator(); it.hasNext();) {
            CourseNode node = it.next();
            if ((id.equals(node.getId()))) {
                return node;
            }
        }
        return null;
    }

    public CourseNode searchCorequisite(String id) {
        for (Iterator<CourseNode> it = prerequisites.iterator(); it.hasNext();) {
            CourseNode node = it.next();
            if ((id.equals(node.getId()))) {
                return node;
            }
        }
        return null;
    }
}
