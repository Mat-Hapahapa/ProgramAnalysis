import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import models.Node;
import models.VariableList;

public class ReachingDefinition {

	public Map<Integer, ArrayList<Node>> nodeList;
	Set<String> leastElement = new HashSet<>();
	VariableList vl = new VariableList();
	
	public ReachingDefinition() {
		nodeList = new HashMap<Integer, ArrayList<Node>>();
	}

	public void initAnalize(Node node) {
		
		int key = node.getFromNode();
		if(!nodeList.containsKey(key)) {
			nodeList.put(key , new ArrayList<Node>());
		}
		
		ArrayList<String> varlist = vl.getVariables(node.getOperation());
		for(String s: varlist) {
			leastElement.add(s);
		}
	}
	
	public void addDeclarations() {
		ArrayList<Node> list = nodeList.get(0);
		for(String variable: leastElement) {
			list.add(new Node(-1, 0, variable));
		}
	}
	
	public boolean eval(Node node) {
		boolean isNew = false;
		
		return isNew;
	}

	public void analize(Node node) {
		
	}
	
	public String toString() {
		return nodeList.toString();
	}
	
	public void printResult() {
		for(Map.Entry<Integer, ArrayList<Node>> entry : nodeList.entrySet()) {
		    Integer key = entry.getKey();
		    ArrayList<Node> value = entry.getValue();

		    System.out.print("q" + key + " --> ");
		    
		    for(Node n: value) {
		    	System.out.print("(" + n.getOperation() + ",");
		    	System.out.print("q" + (n.getFromNode() == -1 ? "?": n.getFromNode()) + ",");
		    	System.out.print("q" + n.getToNode() + "),");
		    }
		    
		    System.out.println();
		}
	}

}
