package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class GraphTraversal
{


	//TODO level 1: implement BFS traversal starting from s
	//Now that you have made sense of the GUI and you have modeled the world, you are ready to give your first try at implementing an algorithm that would allow you to find a path to the safe house from a given tile. Being a resourceful developer, you quickly open the book ”Introduction to Algorithms”[4], a holy grail of Algorithm design, and remember reading something about Breadth First Search(BFS) and Depth First Search(DFS). You decide to implement these two algorithms to see if they do the job.
	//Open the GraphTraversal class and implement the two following static methods:
	//• BFS(Tile start) : This method takes a Tile as input which represents the starting point of the traversal. It will then traverse the map and find all the reachable tiles from the given input tile using BFS. It returns an ArrayList containing the Tiles in the same order as they have been visited.
	//• DFS(Tile start) : This method takes a Tile as input which represents the starting point of the traversal. It will then traverse the map and find all the reachable tiles from the given input tile using DFS. It return an ArrayList containing the Tiles in the same order as they have been visited.
	//NOTE: Some tiles are not designed to be traveled through, thus you can use the method isWalkable() from the Tile class to avoid these obstacle tiles in your traversals.
	public static ArrayList<Tile> BFS(Tile s) {
		//TODO level 1: implement BFS traversal starting from s

		LinkedList<Tile> queue = new LinkedList<>();
		ArrayList<Tile> visited = new ArrayList<>();
		queue.add(s);
		visited.add(s);
		while (!queue.isEmpty()) {
			Tile current = queue.poll();
			for (Tile t : current.neighbors) {
				if (!visited.contains(t) && t.isWalkable()) {
					queue.add(t);
					visited.add(t);
				}
			}
		}
		return visited;
	}



	//TODO level 1: implement DFS traversal starting from s
	public static ArrayList<Tile> DFS(Tile s) {
		//TODO level 1: implement DFS traversal starting from s
		LinkedList<Tile> stack = new LinkedList<>();
		ArrayList<Tile> visited = new ArrayList<>();
		stack.push(s);
		visited.add(s);
		while (!stack.isEmpty()) {
			Tile current = stack.pop();
			for (Tile t : current.neighbors) {
				if (!visited.contains(t) && t.isWalkable()) {
					stack.push(t);
					visited.add(t);
				}
			}
		}
		return visited;
	}

}  