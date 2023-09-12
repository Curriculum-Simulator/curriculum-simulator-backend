package alahyaoui.curriculum.business;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class CourseNode_addPrerequisite_72d5a07885_Test {

    private CourseNode courseNode;

    @BeforeEach
    public void setup() {
        courseNode = new CourseNode();
    }

    @Test
    public void testAddPrerequisite_success() {
        CourseNode prerequisite = mock(CourseNode.class);
        courseNode.addPrerequisite(prerequisite);
        
        Set<CourseNode> prerequisites = courseNode.getPrerequisites();
        Iterator<CourseNode> iterator = prerequisites.iterator();
        CourseNode actualPrerequisite = iterator.next();
        
        assertTrue(actualPrerequisite.equals(prerequisite), "Prerequisite should be added successfully");
    }

    @Test
    public void testAddPrerequisite_failure() {
        CourseNode prerequisite = mock(CourseNode.class);
        courseNode.addPrerequisite(prerequisite);
        courseNode.addPrerequisite(prerequisite);
        
        Set<CourseNode> prerequisites = courseNode.getPrerequisites();
        Iterator<CourseNode> iterator = prerequisites.iterator();
        iterator.next();
        assertFalse(iterator.hasNext(), "Duplicate prerequisite should not be added");
    }
}
