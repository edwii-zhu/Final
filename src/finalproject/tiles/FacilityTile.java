package finalproject.tiles;

import finalproject.system.Tile;
import finalproject.system.TileType;

public class FacilityTile extends Tile {
    //TODO level 0: finish constructor
    public FacilityTile() {
        super();
        this.type = TileType.Facility;
        distanceCost = 1.0;
        timeCost = 2.0;
        damageCost = 0.0;
    }
}
