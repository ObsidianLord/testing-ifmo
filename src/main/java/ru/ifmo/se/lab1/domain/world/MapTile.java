package ru.ifmo.se.lab1.domain.world;

public abstract class MapTile {
    public MapTileType type;
    public Decoration decoration;

    public MapTile(MapTileType type, Decoration decoration) {
        this.type = type;
        this.decoration = decoration;
    }

    @Override
    public String toString() {
        return "Локация (тип: " + type.name() + ", местность: " + decoration.name() + ")";
    }
}
