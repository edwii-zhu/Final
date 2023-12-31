package finalproject;

import java.util.ArrayList;
import java.util.HashSet;

import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.*;

public class Graph {
    ArrayList<Tile> vertices;
    int graphSize;
    ArrayList<Edge>[] adjacentTiles;
    Edge [][] adjMatrix;

    // TODO level 2: Add fields that can help you implement this data type
    // TODO level 2: initialize and assign all variables inside the constructor

    public Graph(ArrayList<Tile> vertices) {
        this.vertices = vertices;
        this.graphSize = vertices.size();
        adjacentTiles = new ArrayList[graphSize];
        adjMatrix = new Edge[graphSize][graphSize];
        for (int i = 0; i < graphSize; i++) {
            adjacentTiles[i] = new ArrayList<Edge>();
        }
    }

    // TODO level 2: add an edge to the graph
    public void addEdge(Tile origin, Tile destination, double weight) {
        Edge newEdge = new Edge(origin, destination, weight);
        adjacentTiles[origin.nodeID].add(newEdge);
        adjMatrix[origin.nodeID][destination.nodeID] = newEdge;
    }

    // TODO level 2: return a list of all edges in the graph
    public ArrayList<Edge> getAllEdges() {
       ArrayList<Edge> allEdges = new ArrayList<>();
        for (int i =0; i < graphSize; i++){
            for (int j = 0; j < graphSize; j++){
                if (adjMatrix[i][j] != null){
                    allEdges.add(adjMatrix[i][j]);
                }
            }
        }
        return allEdges;
    }

    // TODO level 2: return list of tiles adjacent to t
    public ArrayList<Tile> getNeighbors(Tile t) {
        ArrayList<Tile> neighbors = new ArrayList<>();
        for (Edge e : adjacentTiles[t.nodeID]) {
            if (e.destination.isWalkable()) {
                neighbors.add(e.destination);
            }
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
    public Tile getDestination(){
            for(Tile t : vertices) {
                if (t.isDestination) {
                    return t;
                }
            }
        return null;
    }

    public double getEdgeWeight(Tile start, Tile end){
        return adjMatrix[start.nodeID][end.nodeID].weight;
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