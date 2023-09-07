package alahyaoui.curriculum.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseGraph_clear_8ed6b55d97_Test {

    @Autowired
    private CourseGraph courseGraph;

    private Set<Node> nodes;

    @BeforeEach
    public void setup() {
        nodes = mock(HashSet.class);
        courseGraph = new CourseGraph();
    }

    @Test
    public void testClear_WhenNodesIsNotEmpty() {
        when(nodes.isEmpty()).thenReturn(false);
        courseGraph.clear();
        assertEquals(true, nodes.isEmpty());
    }

    @Test
    public void testClear_WhenNodesIsEmpty() {
        when(nodes.isEmpty()).thenReturn(true);
        courseGraph.clear();
        assertEquals(true, nodes.isEmpty());
    }
}
