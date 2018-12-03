package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WorklistFIFO {

    public List<Node> worklist;

    public WorklistFIFO() {
        worklist = new ArrayList<Node>();
    }


    public boolean isEmpty() {
        return worklist.isEmpty();
    }

    public void insert(Node constrain) {
        worklist.add(constrain);
    }

    public Node extract() {
        Node constrain = worklist.get(0);
        worklist.remove(0);
        return constrain;
    }
    
    public String toString() {
    	return worklist.toString();
    }
    
    public void pa4funDisplay() {
    	for(Node n: worklist) {	
    		if(!n.getOperation().contains("end") || n.getToNode()==-1) {
    			int nodeFrom = n.getFromNode();
    			int nodeTo = n.getToNode();
    			
    			int nodeId = nodeFrom;
    			
    			if(nodeTo == -1) {
    				nodeId= nodeTo;
    			}
    			
    			String nodeIdStr = String.valueOf(nodeId);
    			if(nodeId == -1) {
    				nodeIdStr = "<";
    			}else if(nodeId == 0) {
    				nodeIdStr = ">";
    			}
    			
    			System.out.print("q" + nodeIdStr + ", ");    			
    		}
    	}
    	
    	System.out.println();
    }
    
    public void initialPa4FunDisplay() {
    	HashSet<Integer> nodeIds = new HashSet<Integer>();
    	for(Node n: worklist) {	
    		nodeIds.add(n.getFromNode());
    		nodeIds.add(n.getToNode());
    	}
    	
    	System.out.print("q>, ");
    	for(int i=0; i<nodeIds.size()-2; i++) {	
    		System.out.print("q" + (i+1) + ", ");
    	}
    	System.out.println("q<");
    }
}
