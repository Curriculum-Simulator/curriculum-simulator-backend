package alahyaoui.curriculum.business;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CourseGraph_addNode_e1e498b2ff_Test {
    
    @Mock
    private Set<CourseNode> nodes;

    @InjectMocks
    private CourseGraph courseGraph;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        nodes = new HashSet<>();
    }

    @Test
    public void testAddNode_Success() {
        CourseNode node = new CourseNode();
        when(nodes.add(node)).thenReturn(true);
        courseGraph.addNode(node);
        verify(nodes).add(node);
        assertTrue(nodes.contains(node));
    }

    @Test
    public void testAddNode_Failure() {
        CourseNode node = new CourseNode();
        when(nodes.add(node)).thenReturn(false);
        courseGraph.addNode(node);
        verify(nodes).add(node);
        assertTrue(!nodes.contains(node));
    }
}
