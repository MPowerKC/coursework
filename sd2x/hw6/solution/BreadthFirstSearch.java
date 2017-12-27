

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * SD2x Homework #6
 * This is an implementation of Breadth First Search (BFS) on a graph.
 * You may modify and submit this code if you'd like.
 */

public class BreadthFirstSearch {
	protected Set<Node> marked;
	protected Graph graph;
	
	class NodeMetadata {
		protected Node node;
		protected int distance;
		
		public NodeMetadata(Node node, int distance) {
			this.node = node;
			this.distance = distance;
		}
		
		public Node getNode() {
			return this.node;
		}
		
		public int getDistance() {
			return this.distance;
		}
	}

	public BreadthFirstSearch(Graph graphToSearch) {
		marked = new HashSet<Node>();
		graph = graphToSearch;
	}
	
	/**
	 * This method was discussed in the lesson
	 */
	public boolean bfs(Node start, String elementToFind) {
		if (!graph.containsNode(start)) {
				return false;
		}
		if (start.getElement().equals(elementToFind)) {
			return true;
		}
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					if (neighbor.getElement().equals(elementToFind)) {
						return true;
					}
					marked.add(neighbor);
					toExplore.add(neighbor);
				}
			}
		}
		return false;
	}

	public int bfsEdges(Node start, String elementToFind) {
		if (!graph.containsNode(start) || !graph.containsElement(elementToFind)) {
			return -1;
		}
		if (start.getElement().equals(elementToFind)) {
			return 0;
		}
		Queue<NodeMetadata> toExplore = new LinkedList<NodeMetadata>();
		marked.add(start);
		toExplore.add(new NodeMetadata(start, 0));
		while (!toExplore.isEmpty()) {
			NodeMetadata current = toExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current.getNode())) {
				if (!marked.contains(neighbor)) {
					if (neighbor.getElement().equals(elementToFind)) {
						return current.getDistance() + 1;
					}
					marked.add(neighbor);
					toExplore.add(new NodeMetadata(neighbor, current.getDistance() + 1));
				}
			}
		}
		return -1;
	}

	public Set<String> bfsNodes(Node start, int distance) {
		if (!graph.containsNode(start) || distance < 1) {
			return null;
		}
		
		Set<String> nodes = new HashSet<String>();

		Queue<NodeMetadata> toExplore = new LinkedList<NodeMetadata>();
		marked.add(start);
		toExplore.add(new NodeMetadata(start, 0));
		while (!toExplore.isEmpty()) {
			NodeMetadata current = toExplore.remove();
			if (current.distance > 0 && current.distance <= distance)
				nodes.add(current.getNode().getElement());
			
			if (current.distance < distance) {
				for (Node neighbor : graph.getNodeNeighbors(current.getNode())) {
					if (!marked.contains(neighbor)) {						
						marked.add(neighbor);
						toExplore.add(new NodeMetadata(neighbor, current.getDistance() + 1));
					}
				}
			}
		}
		return nodes;
	}

}
