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
			costGraph = new Graph(vertices);
			damageGraph = new Graph(vertices);
			aggregatedGraph = new Graph(vertices);
			for (Tile t : vertices) {
				ArrayList<Tile> neighbors = t.neighbors;
				for (Tile neighbor : neighbors) {
					if (t.type == TileType.Metro && neighbor.type == TileType.Metro) {
						MetroTile metroT = (MetroTile) t;
						MetroTile metroN = (MetroTile) neighbor;
						metroT.fixMetro(metroN);
						costGraph.addEdge(t, neighbor, metroT.metroDistanceCost);
						damageGraph.addEdge(t, neighbor, metroT.damageCost);
						aggregatedGraph.addEdge(t, neighbor, metroT.damageCost);
					} else if (t.isWalkable() && neighbor.isWalkable()) {
						costGraph.addEdge(t, neighbor, neighbor.distanceCost);
						damageGraph.addEdge(t, neighbor, neighbor.damageCost);
						aggregatedGraph.addEdge(t, neighbor, neighbor.damageCost);
					}
				}
			}
		}
	}

	/*
• findPath : Override the findPath(startNode, waypoints) method from the parent class. This method implement the LARAC algorithm that finds the optimal path with our limited HP. Note that the total cost of a path is equal to the sum of the weights of the edges that belong to the path. Now, the algorithm consists of the following steps:
	1. Set the Graph field from the superclass to be equal to costGraph, and find the optimal path pc with the least distance cost. If the total damage cost for pc is less than our health H, return pc for we have found the optimal path.
	2. Set the Graph field from the superclass to be equal to damageGraph, find the optimal path pd with the least damage cost. If the total damage cost for pd is bigger than our health H, return null for no possible path exists.
	3. Compute the multiplier λ using the equation:
	λ = c(pc) − c(pd)
	d(pd) − d(pc)
	where c(p) is the total distance cost for a path p and d(p) is the total damage cost for a path p. Then update each aggregatedGraph’s edge weight to the latest aggregated cost cλ = c + λ ∗ d., where c is the distance cost of the edge, and d is the damage cost of the edge.
	17
	4. Set the Graph field from the superclass to be equal to aggregatedGraph and compute the optimal path pr with the least aggregate cost.
– If the total aggregated cost for pr is the same as the total aggregated cost for pc (our current shortest path without considering any damage factor), then return pd (our current safest path).
– else if the total damage cost for pr is less than or equal to our HP then assign pr to pd.
– else assign pr to pc.
	5. Repeat Step 3 until a path is returned.
*/
	@Override
	public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints) {
		ArrayList<Tile> pc, pd, pr;
		double lambda;

		// Step 1
		this.g = this.costGraph;
		pc = super.findPath(start, waypoints);
		if (this.damageGraph.computePathCost(pc) < this.health) {
			return pc;
		}

		// Step 2
		this.g = this.damageGraph;
		pd = super.findPath(start, waypoints);
		if (this.damageGraph.computePathCost(pd) > this.health) {
			return null;
		}

		// Step 3 and 4
		do {
			lambda = (this.costGraph.computePathCost(pc) - this.costGraph.computePathCost(pd)) /
					(this.damageGraph.computePathCost(pd) - this.damageGraph.computePathCost(pc));
			for (Graph.Edge e : this.aggregatedGraph.getAllEdges()) {
				e.weight = e.destination.distanceCost + lambda * e.destination.damageCost;
			}

			this.g = this.aggregatedGraph;
			pr = super.findPath(start, waypoints);

			if (this.aggregatedGraph.computePathCost(pr) == this.aggregatedGraph.computePathCost(pc)) {
				return pd;
			} else if (this.damageGraph.computePathCost(pr) <= this.health) {
				pd = pr;
			} else {
				pc = pr;
			}
		} while (true);
	}
}