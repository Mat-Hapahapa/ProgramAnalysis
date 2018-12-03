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
		nodeList.put(-1 , new ArrayList<Node>());
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
			list.add(new Node(-2, 0, variable));
		}
	}
	
	public boolean eval(Node node, ArrayList<Node> visitedNodes) {
		//(RD(qo) \ killRD(qo, alpha, q.)) U genRD(qo, alpha, q.) ~C RD(q.)
		//boolean visited = false;
		boolean newInfo = false;
		
		ArrayList<Node> fromNodeList = nodeList.get(node.getFromNode());
		ArrayList<Node> toNodeList = nodeList.get(node.getToNode());
        
//        for(Node n: visitedNodes) {
//            visited = visited || node.equals(n);
//        }
	
		boolean nodeIsIn = false;
		
		
		for(Node fromNode: fromNodeList) {
			boolean isNodeFromInside = false;
			for(Node toNode: toNodeList) {
				isNodeFromInside = isNodeFromInside || fromNode.equals(toNode);
			}
			
			newInfo = newInfo || !isNodeFromInside;
		}
		
		return newInfo;
		
		
//		if(visited) {
//			System.out.println("Node: " + node.toString());
//			System.out.println("visited:" + visitedNodes.toString());
//		}
		
		//return !visited;
	}

	public void analize(Node node) {
		String op = node.getOperation();
		String variable = "";
		ArrayList<Node> myList = nodeList.get(node.getToNode());
        boolean isAsignmentDuplicated = true;
		
		if (op.contains(":=")){
        	boolean isIn = false;
    		for(Node myNode: myList) {
    			isIn = isIn || myNode.equals(node);
    		}
    		
    		if(!isIn) {
    			myList.add(node);
    			isAsignmentDuplicated = false;
    		}
    		variable = op.substring(0, op.indexOf(":="));
        }
 
        //Add info from previous node
        ArrayList<Node> nl = nodeList.get(node.getFromNode());
        for(Node n : nl) {
        	if(n.getFromNode() == -2 && n.getOperation().equals(variable)) { //InitialNode with unknown variable
        		//Nothing
        		
        	}else {   		
        		boolean isIn = false;
        		for(Node myNode: myList) {
        			isIn = isIn || myNode.equals(n);
        		}
        		
        		if(!isIn && (isAsignmentDuplicated || !n.getOperation().contains(variable))) {
        			myList.add(n);
        		}
        	}
        }
	}
	
	public String toString() {
		return nodeList.toString();
	}
	
	public void printResult() {
		for(Map.Entry<Integer, ArrayList<Node>> entry : nodeList.entrySet()) {
		    Integer key = entry.getKey();
		    ArrayList<Node> value = entry.getValue();
		    
		    String keyStr = key.toString();
		    if(key == 0) {
		    	keyStr = ">";
		    }else if(key==-1) {
		    	keyStr = "<";
		    }

		    System.out.print("q" + keyStr + " --> ");
		    
		    for(Node n: value) {
		    	String op = n.getOperation();
		    	Integer idx = op.indexOf(':');
		    	String variable = idx > -1 ? op.substring(0, idx): op;
		    	
		    	System.out.print("(" + variable + ",");
		    	System.out.print("q" + (n.getFromNode() == -2 ? "?": n.getFromNode()) + ",");
		    	System.out.print("q" + (n.getToNode()==-1 ? "<" : n.getToNode()) + "),");
		    }
		    
		    System.out.println();
		}
	}

}
