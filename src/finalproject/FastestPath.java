package finalproject;

import java.util.ArrayList;

import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.MetroTile;

public class FastestPath extends PathFindingService {
    //TODO level 6: find time prioritized path
    public FastestPath(Tile start) {
        super(start);
        generateGraph();

    }

	@Override
	public void generateGraph() {
		// TODO Auto-generated method stub
        {
            int id = 0;
            ArrayList<Tile> vertices = GraphTraversal.BFS(source);
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
                        g.addEdge(t, neighbor, metroT.metroTimeCost);
                    }
                    else if(t.isWalkable() && neighbor.isWalkable()){
                        g.addEdge(t, neighbor, neighbor.timeCost);
                    }
                }
            }
        }
	}

}
