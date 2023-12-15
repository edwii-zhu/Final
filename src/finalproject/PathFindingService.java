package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class PathFindingService {
	Tile source;
	Graph g;
	
	public PathFindingService(Tile start) {
    	this.source = start;
    }

	public abstract void generateGraph();
    {
        ArrayList<Tile> vertices = new ArrayList<>();
        Tile current = source;
        while(!current.isDestination){
            vertices.add(current);
            for(int i = 0; i < current.neighbors.size(); i++){
                if(!vertices.contains(current.neighbors.get(i))){
                    vertices.add(current.neighbors.get(i));
                }

            }
            current = current.neighbors.getFirst();
        }
    }



    //TODO level 4: Implement basic dijkstra's algorithm to find a path to the final unknown destination
    public ArrayList<Tile> findPath(Tile startNode) {
        // WARNING THIS SHIT DONT WORK
        /*
    }
        Tile start = startNode;
        TilePriorityQ pq = new TilePriorityQ(g.vertices);
        pq.add(start);
        while(!pq.isEmpty()){
            Tile current = pq.removeMin(;
            if(current.isDestination){
                return current.path;
            }
            for(int i = 0; i < current.neighbors.size(); i++){
                Tile neighbor = current.neighbors.get(i);
                if(!pq.contains(neighbor)){
                    pq.add(neighbor);
                }
                double newCost = current.costSoFar + g.getEdgeWeight(current, neighbor);
                if(newCost < neighbor.costSoFar){
                    neighbor.costSoFar = newCost;
                    neighbor.path = current.path;
                    neighbor.path.add(current);
                }
            }
        }*/return null;}
    
    //TODO level 5: Implement basic dijkstra's algorithm to path find to a known destination
    public ArrayList<Tile> findPath(Tile start, Tile end) {
    	return null;
    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to the final destination passing through given waypoints
    public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints){
    	return null;
    }
        
}

