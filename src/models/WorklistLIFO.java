package models;

import java.util.ArrayList;
import java.util.List;

public final class WorklistLIFO {
	
	public List<Node> worklist;

	public WorklistLIFO() {
		worklist = new ArrayList<Node>();
	}
	
	public List<Node> getWorklist() {
		return worklist;
	}

    public List<Node> insert(Node constrain) {
        worklist.add(0, constrain);
        return worklist;
    }

    public Node extract() {
        Node nextTask = worklist.get(0);
        worklist.remove(0);
        return nextTask;
    }

    public void isEmpty() {
        // Not used - constructor makes an empty list
    }
    
    
    
}
