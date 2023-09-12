package alahyaoui.curriculum.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseGraph_addNodes_21cf27ff32_Test {
    private CourseGraph courseGraph;
    private List<CourseNode> mockNodes;

    @BeforeEach
    public void setUp() {
        courseGraph = new CourseGraph();
        mockNodes = new ArrayList<>();
        CourseNode mockNode1 = mock(CourseNode.class);
        CourseNode mockNode2 = mock(CourseNode.class);
        mockNodes.add(mockNode1);
        mockNodes.add(mockNode2);
    }

    @Test
    public void testAddNodes() {
        courseGraph.addNodes(mockNodes);
        assertEquals(2, courseGraph.getNodes().size());
        assertTrue(courseGraph.getNodes().containsAll(mockNodes));
    }

    @Test
    public void testAddNodesWithEmptyList() {
        List<CourseNode> emptyList = new ArrayList<>();
        courseGraph.addNodes(emptyList);
        assertEquals(0, courseGraph.getNodes().size());
    }
}
