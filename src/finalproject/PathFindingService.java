package finalproject;

import finalproject.system.Tile;
import finalproject.Graph.*;


import java.util.ArrayList;
import java.util.LinkedList;

public abstract class PathFindingService {
	Tile source;
	Graph g;
    ArrayList<Tile> s = new ArrayList<>();

	public PathFindingService(Tile start) {
    	this.source = start;
        generateGraph();
    }

	public abstract void generateGraph();

    public void relax(Tile u, Tile v, TilePriorityQ Q){
        double weight = g.getEdgeWeight(u, v);

        if(v.costEstimate > u.costEstimate + weight){
            v.costEstimate = u.costEstimate + weight;
            v.predecessor = u;
            Q.updateKeys(v,u,v.costEstimate);
        }
    }
    public void initializeSingleSource(Tile start){
        for(Tile t : g.vertices){
            t.costEstimate = Double.POSITIVE_INFINITY;
            t.predecessor = null;
        }
        start.costEstimate = 0;
    }

    public ArrayList<Tile> dijkstra(Tile start, TilePriorityQ Q){
        //make dijkstra's algorithm without modifying the graph
        initializeSingleSource(start);

        while(Q.size > 0){
            Tile u = Q.removeMin();
            s.add(u);
            for(Tile v : g.getNeighbors(u)){
                relax(u, v, Q);
            }
        }
        return s;
    }
    //TODO level 4: Implement basic dijkstra's algorithm to find a path to the final unknown destination
    public ArrayList<Tile> findPath(Tile startNode) {
        TilePriorityQ Q = new TilePriorityQ(g.vertices);
        dijkstra(startNode, Q);
        Tile curr = g.getDestination();

        ArrayList<Tile> path = new ArrayList<>();

        while(curr != startNode){
            path.addFirst(curr);
            curr = curr.predecessor;
        }
        path.addFirst(startNode);
        return path;
    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to a known destination
    public ArrayList<Tile> findPath(Tile start, Tile end) {
        ArrayList<Tile> path = new ArrayList<>();
        dijkstra(start, new TilePriorityQ(g.vertices));
        Tile current = end;
        while(current != start){
            path.addFirst(current);
            current = current.predecessor;
        }
        return path;
    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to the final destination passing through given waypoints
    public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints){
        // Step 2: Implement the last and final findPath method, which takes a starting node and a list of waypoints as input. This method builds the shortest paths from the source to the destination, making sure to visit the each of the waypoints in the order in which they have been provided as input. Use the other methods that you have already implemented to help you find such path. Please note that: the destination tile will not be provided within the list of waypoints. You can figure out which one is the destination tile by accessing the field isDestination from the Tile class
        if (waypoints.isEmpty()){
            return findPath(start);
        }
        ArrayList<Tile> path = new ArrayList<>();
        Tile current = start;
        for(Tile t : waypoints){
            ArrayList<Tile> subPath = findPath(current, t);
            path.addAll(subPath);
            current = t;
        }
        ArrayList<Tile> subPath = findPath(current);
        path.addAll(subPath);
        return path;
    }
}


