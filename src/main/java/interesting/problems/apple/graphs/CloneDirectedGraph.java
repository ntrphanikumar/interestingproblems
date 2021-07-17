package interesting.problems.apple.graphs;

import java.util.*;

public class CloneDirectedGraph {
    public Node cloneGraph(Node node) {
        return deepClone(node, new HashSet<>(), new LinkedHashMap<>());
    }

    private Node deepClone(Node node, Set<Integer> visited, Map<Integer, Node> graph) {
        if (node == null) {
            return null;
        }
        if (!graph.containsKey(node.val)) {
            graph.put(node.val, new Node(node.val));
        }
        visited.add(node.val);
        Node clonedNode = graph.get(node.val);
        for (Node neighbour : node.neighbors) {
            if (!graph.containsKey(neighbour.val)) {
                graph.put(neighbour.val, new Node(neighbour.val));
            }
            clonedNode.neighbors.add(graph.get(neighbour.val));
            if (!visited.contains(neighbour.val)) {
                deepClone(neighbour, visited, graph);
            }
        }
        return clonedNode;
    }

    static class Node {
        int val;
        List<Node> neighbors;

        public Node(int val) {
            this.val = val;
        }
    }
}
