package alahyaoui.curriculum.business;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseNode_searchPrerequisite_c6973be207_Test {

    private Set<CourseNode> prerequisites;
    private CourseNode courseNode1;
    private CourseNode courseNode2;
    private CourseNode courseNode3;

    @BeforeEach
    public void setUp() {
        prerequisites = new HashSet<>();
        courseNode1 = new CourseNode("101", "Maths");
        courseNode2 = new CourseNode("102", "Physics");
        courseNode3 = new CourseNode("103", "Chemistry");
        prerequisites.add(courseNode1);
        prerequisites.add(courseNode2);
        prerequisites.add(courseNode3);
    }

    @Test
    public void testSearchPrerequisite_Success() {
        CourseNode result = searchPrerequisite("102");
        assertEquals(courseNode2, result);
    }

    @Test
    public void testSearchPrerequisite_Failure() {
        CourseNode result = searchPrerequisite("104");
        assertNull(result);
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

    public static class CourseNode {
        String id;
        String name;

        CourseNode(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
