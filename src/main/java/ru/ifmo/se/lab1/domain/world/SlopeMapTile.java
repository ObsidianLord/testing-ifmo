package ru.ifmo.se.lab1.domain.world;

public class SlopeMapTile extends MapTile {
    public Direction tilt;

    public SlopeMapTile(Direction tilt, Decoration decoration) {
        super(MapTileType.SLOPE, decoration);
        this.tilt = tilt;
    }
}
