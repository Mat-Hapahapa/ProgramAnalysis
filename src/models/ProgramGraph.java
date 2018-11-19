package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramGraph {

    List<Node> nodes;

    public ProgramGraph(ArrayList<String> path) {
    	this.nodes = new ArrayList<Node>();
    	
    	for(String p: path) {
    		List<String> pList = Arrays.asList(p.trim().split(","));
    		
    		// Node (1,2,x:=0)
    		int startNode = Integer.valueOf(pList.get(0));
    		int endNode = Integer.valueOf(pList.get(1)); 
    		String op = pList.get(2);
    		
    		nodes.add(new Node(startNode, endNode, op));
    	}
    	
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
    
    public void printGraph() {
    	for(Node n: this.nodes) {
    		System.out.println(n.toString());
    	}
    }
}
