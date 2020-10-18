package ru.ifmo.se.lab1.domain;

import ru.ifmo.se.lab1.domain.world.Color;

public class Scarf {
    private Color color;

    public Scarf(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
