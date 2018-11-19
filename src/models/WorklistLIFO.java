package models;

import java.util.List;

public final class WorklistLIFO {

    public static List<Node> insert(Node constrain, List<Node> worklist) {
        worklist.add(0, constrain);
        return worklist;
    }

    public static Node extract(List<Node> worklist) {
        Node nextTask = worklist.get(0);
        worklist.remove(0);
        return nextTask;
    }

    public static boolean isEmpty(List<Node> worklist) {
        return worklist.isEmpty();
    }
}
