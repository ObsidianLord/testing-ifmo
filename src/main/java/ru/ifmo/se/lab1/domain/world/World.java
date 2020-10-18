package ru.ifmo.se.lab1.domain.world;

public class World {
    private MapTile[][] map;
    private Weather weather;

    public World(MapTile[][] map, Weather weather) {
        this.map = map;
        this.weather = weather;
    }

    public MapTile[][] getMap() {
        return map;
    }

    public Weather getWeather() {
        return weather;
    }
}
