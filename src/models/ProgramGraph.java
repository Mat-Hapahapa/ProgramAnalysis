package models;

import java.util.ArrayList;
import java.util.List;

public class ProgramGraph {

    List<Node> nodes = new ArrayList<Node>();

    public ProgramGraph() {

    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(Node node){
        this.nodes.add(node);
    }

    public Node getNode(int i) {
        return this.nodes.get(i);
    }
}
