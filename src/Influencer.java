import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Node;

public class Influencer {
	
    public Map<Integer, ArrayList<Node>> nodeList;

	public Influencer() {
        nodeList = new HashMap<Integer, ArrayList<Node>>();
	}

	public void initFromNode(Node node) {
        nodeList.put(node.getFromNode(), new ArrayList<Node>());
        nodeList.put(node.getToNode(), new ArrayList<Node>());
	}

	public void generateList(Node node) {
        nodeList.get(node.getFromNode()).add(node);
	}
	
	public String toString() {
		return nodeList.toString();
	}

}
