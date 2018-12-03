package models;

import java.util.Objects;

public class Node {

    private int fromNode;
    private int toNode;
    private String operation;

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
    
    public String toString() {
    	return "(x" + (this.fromNode == 0 ? ">": this.fromNode) + ", x" + (this.toNode==-1? "<" : this.toNode) + ", " + this.operation + ")";
    }
    
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Node)) {
            return false;
        }
        Node node = (Node) o;
        return  fromNode == node.fromNode &&
        		toNode == node.toNode &&
                Objects.equals(operation, node.operation);
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(fromNode, toNode, operation);
    }
}
