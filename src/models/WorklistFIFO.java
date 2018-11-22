package models;

import java.util.ArrayList;
import java.util.List;

public class WorklistFIFO {

    public List<Node> empty(){
        return new ArrayList<Node>();
    }

    public List<Node> insert(Node constrain, List<Node> worklist) {
        worklist.add(constrain);
        return worklist;
    }

    public void extract(List<Node> worklist) {

    }
}
