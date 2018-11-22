import MicroC.Array;
import models.Node;
import models.VariableList;

import java.util.*;

public class SignDetection {

    VariableList variableList = new VariableList();

    public Map<Integer, ArrayList<Node>> nodeList;


    public SignDetection() {
        nodeList = new HashMap<Integer, ArrayList<Node>>();



    }

    public void initAnalize(Node node){

    }

    public void analize(Node node) {
    ArrayList<String> varList = variableList.getVariables(node.getOperation());


    }


}
