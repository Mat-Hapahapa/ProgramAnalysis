package models;

import java.util.ArrayList;
import java.util.List;

public class WorklistFIFO {

    public List<Node> worklist;

    public WorklistFIFO() {
        worklist = new ArrayList<Node>();
    }


    public void empty(){
        // Not used - constructor makes an empty list
    }

    public void insert(Node constrain) {
        worklist.add(constrain);
    }

    public Node extract() {
        Node constrain = worklist.get(0);
        worklist.remove(0);
        return constrain;
    }
}
