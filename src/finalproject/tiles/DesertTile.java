package finalproject.tiles;

import finalproject.system.Tile;
import finalproject.system.TileType;

public class DesertTile extends Tile {
    public DesertTile() {
        super();
        this.type = TileType.Desert;
        distanceCost = 2.0;
        timeCost = 6.0;
        damageCost = 3.0;
    }
}
