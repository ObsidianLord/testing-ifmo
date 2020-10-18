package ru.ifmo.se.lab1.domain;

import ru.ifmo.se.lab1.domain.world.World;

public abstract class Movable {
    protected World world;
    protected int x;
    protected int y;

    public Movable(World world, int x, int y) {
        this.world = world;
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSameLocation(Movable movable) {
        return this.world == movable.world && this.x == movable.x && this.y == movable.y;
    }
}
