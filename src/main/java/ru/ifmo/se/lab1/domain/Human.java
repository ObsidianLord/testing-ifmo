package ru.ifmo.se.lab1.domain;

import ru.ifmo.se.lab1.domain.world.Color;
import ru.ifmo.se.lab1.domain.world.Weather;
import ru.ifmo.se.lab1.domain.world.World;

import java.util.Objects;

public class Human extends Movable {
    private String name;
    private Transport transport;
    private Scarf scarf;

    public Human(World world, int x, int y, String name, Scarf scarf) {
        super(world, x, y);
        this.name = name;
        this.scarf = scarf;
    }

    public void getIntoTransport(Transport transport) {
        if (transport.addPassenger(this)) {
            if (this.transport != null) {
                this.transport.removePassenger(this);
            }
            this.transport = transport;
        }
    }

    public void getOutOfTransport() {
        transport.removePassenger(this);
        transport = null;
    }

    public boolean isScarfShining() {
        boolean flag = world.getWeather() == Weather.SUNNY && scarf.getColor() == Color.ORANGE;
        System.out.println(flag ? scarf.getColor().name() + " шафр горит на солнце" : "");
        return flag;
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        System.out.println("Персонаж " + name + " на транспорте " + transport.getName() + " переместился по координате [" + x + ", " + y + "] в " + world.getMap()[y][x].toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return name.equals(human.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
