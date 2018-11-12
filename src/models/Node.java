package models;

public class Node {

    int fromNode;
    int toNode;
    String operation;

    public Node(int fromNode, int toNode, String operation) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.operation = operation;
    }

    public int getFromNode() {
        return fromNode;
    }

    public void setFromNode(int fromNode) {
        this.fromNode = fromNode;
    }

    public int getToNode() {
        return toNode;
    }

    public void setToNode(int toNode) {
        this.toNode = toNode;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
