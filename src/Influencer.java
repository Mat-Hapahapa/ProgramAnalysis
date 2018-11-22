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
	}

	public void generateList(Node node) {
		int key = node.getFromNode();
		ArrayList<Node> list = nodeList.get(key);
		
		// Add node to the list
		list.add(node);
		// Save the new list into the map
		nodeList.put(key, list);
		
	}

}
