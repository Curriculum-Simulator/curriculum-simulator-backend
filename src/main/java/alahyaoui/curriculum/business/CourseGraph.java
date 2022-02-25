package alahyaoui.curriculum.business;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CourseGraph {

    private Set<CourseNode> nodes;// = new HashSet<>();

    public CourseGraph() {
        nodes = new HashSet<>();
    }

    public void addNode(CourseNode node) {
        nodes.add(node);
    }

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

}