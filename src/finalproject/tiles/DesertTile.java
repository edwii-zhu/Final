package finalproject.tiles;

import finalproject.system.Tile;
import finalproject.system.TileType;

public class DesertTile extends Tile {
    public DesertTile() {
        this.type = TileType.Desert;
        distanceCost = 2.0;
        timeCost = 6.0;
        damageCost = 3.0;
    }
}
