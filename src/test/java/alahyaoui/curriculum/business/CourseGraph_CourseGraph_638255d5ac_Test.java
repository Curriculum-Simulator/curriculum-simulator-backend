package alahyaoui.curriculum.business;

import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.stereotype.Component;

@Component
public class CourseGraph_CourseGraph_638255d5ac_Test {

    private CourseGraph courseGraph;

    @BeforeEach
    public void setUp() {
        courseGraph = new CourseGraph();
    }

    @Test
    public void testCourseGraphInitialization() {
        assertNotNull(courseGraph);
        // We can't directly access the private field 'nodes', 
        // so we need to use a getter method if it exists. 
        // If it doesn't exist, you need to create it in the CourseGraph class.
        assertTrue(courseGraph.getNodes() instanceof HashSet);
        assertTrue(courseGraph.getNodes().isEmpty());
    }

    @Test
    public void testCourseGraphAddNode() {
        // We can't directly use the CourseNode's constructor without arguments,
        // so we need to provide the required string argument.
        CourseNode node = new CourseGraph.CourseNode("test");
        // We can't directly access the private field 'nodes', 
        // so we need to use a method to add a node if it exists. 
        // If it doesn't exist, you need to create it in the CourseGraph class.
        courseGraph.addNode(node);
        assertEquals(1, courseGraph.getNodes().size());
        assertTrue(courseGraph.getNodes().contains(node));
    }

}
