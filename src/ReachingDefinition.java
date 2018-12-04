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
		//nodeList.put(-1 , new ArrayList<Node>());
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
			list.add(new Node(0, 0, variable));
		}
	}
	
	public boolean eval(Node node) {
		//(RD(qo) \ killRD(qo, alpha, q.)) U genRD(qo, alpha, q.) ~C RD(q.)
		boolean newInfo = false;
		
		ArrayList<Node> fromNodeList = nodeList.get(node.getFromNode());
		ArrayList<Node> toNodeList = nodeList.get(node.getToNode());
        
//        for(Node n: visitedNodes) {
//            visited = visited || node.equals(n);
//        }
		
		for(Node fromNode: fromNodeList) {
			boolean isNodeFromInside = false;
			for(Node toNode: toNodeList) {
				isNodeFromInside = isNodeFromInside || fromNode.equals(toNode);
			}
			
			newInfo = newInfo || !isNodeFromInside;
		}
		
		return newInfo;
	}

	public boolean analize(Node node) {
		String op = node.getOperation();
		String variable = "";
		boolean newInfo = false;
		ArrayList<Node> fromNodeList = nodeList.get(node.getFromNode());
		ArrayList<Node> tmpList = new ArrayList<Node>();
		
		if (op.contains(":=")){
			variable = op.substring(0, op.indexOf(":="));
        	
    		for(Node myNode: fromNodeList) {
    			if(myNode.getOperation().contains(variable)) {
    				tmpList.add(new Node(myNode.getFromNode(),myNode.getToNode(), variable));
    			} else {
    				tmpList.add(myNode);
    			}
    		}
    		
		} else {
			tmpList.addAll(nodeList.get(node.getFromNode()));
		}
		nodeList.put(node.getToNode(), tmpList);
		
		
		 
		return false;
		 
		
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
