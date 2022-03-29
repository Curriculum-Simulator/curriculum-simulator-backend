package alahyaoui.curriculum.business;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import lombok.Value;

@Value
public class CourseNode {

    private final String id;

    // Creating a set of CourseNode objects that are the prerequisites for the current course.
    private final Set<CourseNode> prerequisites;

    // Creating a set of CourseNode objects that are the corequisites for the current course.
    private final Set<CourseNode> corequisites;

    /**
     * This is the constructor of the CourseNode class. It initializes the id, the prerequisites and corequisites sets.
     * @param id the CourseNode id.
     */
    public CourseNode(String id){
        this.id = id;
        prerequisites = new HashSet<>();
        corequisites = new HashSet<>();
    }

    /**
     * Add a prerequisite to the course
     * 
     * @param prerequisite The prerequisite course.
     */
    public void addPrerequisite(CourseNode prerequisite){
        prerequisites.add(prerequisite);
    }

    /**
     * Add a corequisite to the list of corequisites
     * 
     * @param corequisite the course that is a corequisite of the current course
     */
    public void addCorequisite(CourseNode corequisite){
        corequisites.add(corequisite);
    }

    /**
     * Given a course ID, return the prerequisite node for that course
     * 
     * @param id The id of the prerequisite course.
     * @return The CourseNode object that matches the id.
     */
    public CourseNode searchPrerequisite(String id) {
        for (Iterator<CourseNode> it = prerequisites.iterator(); it.hasNext();) {
            CourseNode node = it.next();
            if ((id.equals(node.getId()))) {
                return node;
            }
        }
        return null;
    }

    /**
     * Search for a prerequisite course in the list of prerequisites
     * 
     * @param id The id of the prerequisite course.
     * @return The CourseNode object that matches the id.
     */
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
