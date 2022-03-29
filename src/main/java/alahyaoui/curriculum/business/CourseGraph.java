package alahyaoui.curriculum.business;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class CourseGraph {

    private Set<CourseNode> nodes;
  
    /**
     *  This is the constructor for the CourseGraph class. It creates a new HashSet of CourseNode objects.
     */
    public CourseGraph() {
        nodes = new HashSet<>();
    }

    /**
     * Add a node to the list of nodes
     * 
     * @param node The node to add to the list.
     */
    public void addNode(CourseNode node) {
        nodes.add(node);
    }

    /**
     * Add a list of nodes to the graph
     * 
     * @param nodes A list of CourseNode objects.
     */
    public void addNodes(List<CourseNode> nodes) {
        for (CourseNode node : nodes) {
            this.nodes.add(node);
        }
    }

    public CourseNode search(String id) {
        for (Iterator<CourseNode> it = nodes.iterator(); it.hasNext();) {
            CourseNode node = it.next();
            if ((id.equals(node.getId()))) {
                return node;
            }
        }
        return null;
    }

    public Set<CourseNode> getNodes() {
        return nodes;
    }

    public void clear(){
        nodes.clear();
    }
}