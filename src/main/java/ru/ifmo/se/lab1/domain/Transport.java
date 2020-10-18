package ru.ifmo.se.lab1.domain;

import ru.ifmo.se.lab1.domain.world.*;

import java.util.HashSet;

public class Transport extends Movable implements Animated {
    private String name;
    private int capacity;
    private int speed;
    private Direction direction;
    private HashSet<Human> passengers;

    public Transport(World world, int x, int y, String name, int capacity) {
        super(world, x, y);
        this.name = name;
        this.capacity = capacity;
        this.speed = 0;
        this.direction = Direction.NORTH;
        this.passengers = new HashSet<Human>();
    }

    public String getName() {
        return name;
    }

    public boolean addPassenger(Human passenger) {
        if (passengers.size() < capacity && passenger.isSameLocation(this)) {
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    public void removePassenger(Human passenger) {
        passengers.remove(passenger);
    }

    @Override
    public void tick() {
        MapTile mapTile = this.world.getMap()[this.y][this.x];
        if (mapTile.type == MapTileType.SLOPE) {
            Direction tilt = ((SlopeMapTile) mapTile).tilt;
            if (speed == 0) {
                direction = tilt;
                keepMoving(1);
                speed = 1;
            } else if (tilt == direction) {
                keepMoving(speed);
                speed += 1;
            } else {
                keepMoving(speed);
                speed -= 1;
            }
        } else {
            keepMoving(speed);
            if (speed > 0) {
                speed -= 1;
            }
        }
    }

    private void keepMoving(int distance) {
        switch (direction) {
            case NORTH:
                move(x, y - distance);
                break;
            case EAST:
                move(x + distance, y);
                break;
            case SOUTH:
                move(x, y + distance);
                break;
            case WEST:
                move(x - distance, y);
                break;
        }
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        if (passengers != null) {
            for (Human passenger: passengers) {
                passenger.move(x, y);
            }
        }
    }
}
