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
		ArrayList<String> varlist = vl.getVariables(node.getOperation());
		for(String s: varlist) {
			leastElement.add(s);
		}
	}

	public void analize(Node node) {

	}

}
