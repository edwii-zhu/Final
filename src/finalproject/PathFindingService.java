package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class PathFindingService {
	Tile source;
	Graph g;

	public PathFindingService(Tile start) {
    	this.source = start;
        generateGraph();
    }

	public abstract void generateGraph();


    public void relax(Tile u, Tile v, double weight){
        if(v.distanceCost > u.distanceCost + weight){
            v.distanceCost = u.distanceCost + weight;
            v.predecessor = u;
        }
    }
    public void initializeSingleSource(Tile start){
        for(Tile t : g.vertices){
            t.distanceCost = Double.POSITIVE_INFINITY;
            t.predecessor = null;
        }
        start.distanceCost = 0;
    }
    public void dijkstra(ArrayList<Tile> vertices, Tile start){
        initializeSingleSource(start);
        ArrayList<Tile> Q = new ArrayList<>();
        for(Tile t : vertices){
            Q.add(t);
        }
        TilePriorityQ PQ = new TilePriorityQ(vertices);
        while(!PQ.isEmpty()){
            Tile u = PQ.removeMin();
            for(Tile v : u.neighbors){
                relax(u, v, v.distanceCost);
            }
        }



    }
    //TODO level 4: Implement basic dijkstra's algorithm to find a path to the final unknown destination
    public ArrayList<Tile> findPath(Tile startNode) {
        dijkstra(g.vertices, startNode);
        return g.vertices;
    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to a known destination
    public ArrayList<Tile> findPath(Tile start, Tile end) {
    	return null;
    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to the final destination passing through given waypoints
    public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints){
    	return null;
    }
        
}

