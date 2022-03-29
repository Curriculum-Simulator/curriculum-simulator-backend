package alahyaoui.curriculum.business;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * This class is used to create a graph of CourseNode objects
 */
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

    /**
     * Search for a node in the tree by its ID
     * 
     * @param id The id of the node to be searched.
     * @return The CourseNode object that matches the id.
     */
    public CourseNode search(String id) {
        for (Iterator<CourseNode> it = nodes.iterator(); it.hasNext();) {
            CourseNode node = it.next();
            if ((id.equals(node.getId()))) {
                return node;
            }
        }
        return null;
    }

    /**
     * Returns the set of nodes in the graph
     * 
     * @return The set of nodes.
     */
    public Set<CourseNode> getNodes() {
        return nodes;
    }

    /**
     * Clear the nodes list.
     */
    public void clear(){
        nodes.clear();
    }
}