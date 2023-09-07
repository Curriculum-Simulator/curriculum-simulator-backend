package alahyaoui.curriculum.business;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseNode_searchCorequisite_56c1c27b6b_Test {

    @Value
    class CourseNode {
        String id;
        Set<CourseNode> prerequisites;
    }

    private Set<CourseNode> prerequisites;

    @BeforeEach
    public void setup() {
        prerequisites = new HashSet<>();
        prerequisites.add(new CourseNode("1", new HashSet<>()));
        prerequisites.add(new CourseNode("2", new HashSet<>()));
        prerequisites.add(new CourseNode("3", new HashSet<>()));
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

    @Test
    public void testSearchCorequisite_success() {
        CourseNode result = searchCorequisite("1");
        Assertions.assertNotNull(result, "Expected non-null result");
        Assertions.assertEquals("1", result.getId(), "Expected id to match");
    }

    @Test
    public void testSearchCorequisite_failure() {
        CourseNode result = searchCorequisite("4");
        Assertions.assertNull(result, "Expected null result for non-existing id");
    }
}
