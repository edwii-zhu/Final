package finalproject;

import java.util.ArrayList;
import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.MetroTile;

public class ShortestPath extends PathFindingService {
    //TODO level 4: find distance prioritized path
    public ShortestPath(Tile start) {
        super(start);
        generateGraph();
//        findPath(start);
    }

    /*

• Step 1: In ShortestPath, implement generateGraph(). The method creates a weighted graph using the distance cost as weight. This graph should be then stored in the appropriate field. To make sure that the graph is generated each time a ShortestPath object is created, you should add a call to this method inside the constructor.
Note: You can use BFS or DFS to help you get a list of all reachable tiles. Remember also that the graph you want to build should only connect tiles that are designed to be travelled through. You can use the method isWalkable() to help you figure out which tiles are not just obstacles.
     */
    @Override
    public void generateGraph() {
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
                        metroN.fixMetro(metroN);
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