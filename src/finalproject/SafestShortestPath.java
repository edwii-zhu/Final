package finalproject;


import java.util.ArrayList;
import java.util.LinkedList;
import finalproject.tiles.MetroTile;
import finalproject.system.TileType;

import finalproject.system.Tile;

public class SafestShortestPath extends ShortestPath {
	public int health;
	public Graph costGraph;
	public Graph damageGraph;
	public Graph aggregatedGraph;

	//TODO level 8: finish class for finding the safest shortest path with given health constraint
	public SafestShortestPath(Tile start, int health) {
		super(start);
		generateGraph();
		this.health = health;
	}

	
	public void generateGraph() {
		// TODO Auto-generated method stub
		{
			int id = 0;
			ArrayList<Tile> vertices = GraphTraversal.DFS(source);
			for (Tile t : vertices) {
				t.nodeID = id;
				id++;
			}
			g = new Graph(vertices);
			for (Tile t : vertices) {
				ArrayList<Tile> neighbors = t.neighbors;
				for (Tile neighbor : neighbors) {
					if (t.type == TileType.Metro && neighbor.type == TileType.Metro){
						MetroTile metroT = (MetroTile) t;
						MetroTile metroN = (MetroTile) neighbor;
						metroT.fixMetro(metroN);
						g.addEdge(t, neighbor, metroT.metroDistanceCost);
					}
					else if(t.isWalkable() && neighbor.isWalkable()){
						g.addEdge(t, neighbor, neighbor.distanceCost);
					}
				}
			}
		}
	}

}
