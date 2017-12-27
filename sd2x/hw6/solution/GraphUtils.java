

import java.util.List;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {
		if (graph == null || src == null || dest == null || !graph.containsElement(src) || !graph.containsElement(dest))
			return -1;
		
		BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
		return bfs.bfsEdges(graph.getNode(src), dest);
	}
	

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
		if (graph == null || src == null || distance < 1 || !graph.containsElement(src)) 
			return null;
		
		BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
		return bfs.bfsNodes(graph.getNode(src), distance);
	}


	public static boolean isHamiltonianPath(Graph g, List<String> values) {
		if (g == null || values == null || g.numNodes +1 != values.size()) 
			return false;
		
		if (!allNodesConnected(g, values))
			return false;
		

		/* IMPLEMENT THIS METHOD! */
		
		return true; // this line is here only so this code will compile if you don't modify it
	}
	
	public static boolean allNodesConnected(Graph g, List<String> values) {
		Node prevNode = null;
		
		for (String element : values) {
			if (!g.containsElement(element))
				return false;
			Node currNode = 	g.getNode(element);
			if (prevNode != null) {
				if (!g.getNodeNeighbors(prevNode).contains(currNode))
					return false;
			}
			prevNode = currNode;
		}
		
		return true;
	}
	
}
