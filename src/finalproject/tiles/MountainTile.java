package finalproject.tiles;

import finalproject.system.Tile;
import finalproject.system.TileType;

public class MountainTile extends Tile {
    //TODO level 0: finish constructor
    public MountainTile() {
        this.type = TileType.Moutain;
        distanceCost = 100.0;
        timeCost = 100.0;
        damageCost = 100.0;
    }
}
