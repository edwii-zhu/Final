package finalproject.tiles;

import finalproject.system.Tile;
import finalproject.system.TileType;

public class PlainTile extends Tile {
    public PlainTile() {
        super();
        this.type = TileType.Plain;
        distanceCost = 3.0;
        timeCost = 1.0;
        damageCost = 0.0;
    }
}
