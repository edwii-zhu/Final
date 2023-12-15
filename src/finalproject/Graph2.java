package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;

public class Graph2 {
    ArrayList<Vertex> vertexes;
    int graphSize;
	// TODO level 2: Add fields that can help you implement this data type
    // TODO level 2: initialize and assign all variables inside the constructor
    public class Vertex<T>{
        ArrayList<Edge> adjList;
        T data;
        boolean visited;
        public Vertex(T data){
            this.data = data;
            adjList = new ArrayList<>();
        }

    }
	public Graph2(ArrayList<Tile> vertices) {
        for (Tile vertex : vertices) {
            Vertex temp = new Vertex(vertex);
            vertexes.add(temp);
        }
        this.graphSize = vertices.size();

	}
	
    // TODO level 2: add an edge to the graph
    public void addEdge(Tile origin, Tile destination, double weight){
        Edge e = new Edge(origin, destination, weight);
        adjacentTiles[origin.nodeID].add(e);
    }
    
    // TODO level 2: return a list of all edges in the graph
	public ArrayList<Edge> getAllEdges() {
        ArrayList<Edge> allEdges = new ArrayList<>();
        for (int i = 0; i < graphSize; i++) {
            allEdges.addAll(adjacentTiles[i]);
        }
        return allEdges;
	}
  
	// TODO level 2: return list of tiles adjacent to t
	public ArrayList<Tile> getNeighbors(Tile t) {
        ArrayList<Tile> neighbors = new ArrayList<>();
        for (Edge e : adjacentTiles[t.nodeID]) {
            neighbors.add(e.destination);
        }
        return neighbors;
    }
	
	// TODO level 2: return total cost for the input path
	public double computePathCost(ArrayList<Tile> path) {
        double totalCost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Tile start = path.get(i);
            Tile end = path.get(i + 1);
            for (Edge e : adjacentTiles[start.nodeID]) {
                if (e.destination == end) {
                    totalCost += e.weight;
                }
            }
        }
        return totalCost;
	}
	
   
    public static class Edge{
    	Tile origin;
    	Tile destination;
    	double weight;

        // TODO level 2: initialize appropriate fields
        public Edge(Tile s, Tile d, double cost){
        	this.origin = s;
            this.destination = d;
            this.weight = cost;
        }
        
        // TODO level 2: getter function 1
        public Tile getStart(){
            return this.origin;
        }

        
        // TODO level 2: getter function 2
        public Tile getEnd() {
            return this.destination;
        }
        
    }
    
}