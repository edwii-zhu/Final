package finalproject.tiles;

import finalproject.system.Tile;
import finalproject.system.TileType;

public class ZombieInfectedRuinTile extends Tile {
    //TODO level 0: finish constructor
    public ZombieInfectedRuinTile() {
        super();
        this.type = TileType.ZombieInfectedRuin;
        distanceCost = 1.0;
        timeCost = 3.0;
        damageCost = 5.0;
    }
}
