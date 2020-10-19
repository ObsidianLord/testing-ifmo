package ru.ifmo.se.lab1;

import org.junit.Assert;
import org.junit.Test;
import ru.ifmo.se.lab1.domain.*;
import ru.ifmo.se.lab1.domain.world.*;

public class RideTest {

    @Test
    public void testCantExceedTransportCapacity() {
        World world = new World(new MapTile[1][1], Weather.SUNNY);
        Human first = new Human(world, 0, 0, "Персонаж 1", null);
        Human second = new Human(world, 0, 0, "Персонаж 2", null);
        Transport bubble = new Transport(world, 0, 0, "Пузырь", 1);
        first.getIntoTransport(bubble);
        second.getIntoTransport(bubble);

        Assert.assertEquals(bubble.getCapacity(), bubble.getPassengers().size());
        Assert.assertTrue(bubble.getPassengers().contains(first));
        Assert.assertFalse(bubble.getPassengers().contains(second));
        Assert.assertEquals(bubble, first.getTransport());
        Assert.assertNull(second.getTransport());
    }

    @Test
    public void testTransportChange() {
        World world = new World(new MapTile[1][1], Weather.SUNNY);
        Human character = new Human(world, 0, 0, "Персонаж 1", null);
        Transport bubble = new Transport(world, 0, 0, "Пузырь", 1);
        Transport ATAT = new Transport(world, 0, 0, "AT-AT", 3);

        character.getIntoTransport(bubble);
        character.getIntoTransport(ATAT);

        Assert.assertEquals(ATAT, character.getTransport());
        Assert.assertTrue(bubble.getPassengers().isEmpty());
        Assert.assertTrue(ATAT.getPassengers().contains(character));
    }

    @Test
    public void testCanGetOutOfTransport() {
        World world = new World(new MapTile[1][1], Weather.SUNNY);
        Human character = new Human(world, 0, 0, "Персонаж 1", null);
        Transport bubble = new Transport(world, 0, 0, "Пузырь", 1);

        character.getIntoTransport(bubble);
        character.getOutOfTransport();

        Assert.assertNull(character.getTransport());
        Assert.assertTrue(bubble.getPassengers().isEmpty());
    }

    @Test
    public void testCanPhysics() {
        World world = new World(new MapTile[][]{
                {
                        new SlopeMapTile(Direction.EAST, Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new SlopeMapTile(Direction.SOUTH, Decoration.GRASS),
                },
                {
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                },
                {
                        new SlopeMapTile(Direction.NORTH, Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new SlopeMapTile(Direction.WEST, Decoration.GRASS),
                },
        }, Weather.SUNNY);
        Human character = new Human(world, 0, 0, "Персонаж 1", null);
        Transport bubble = new Transport(world, 0, 0, "Пузырь", 1);

        character.getIntoTransport(bubble);

        bubble.tick();
        Assert.assertEquals(1, character.getX());
        Assert.assertEquals(0, character.getY());

        bubble.tick();
        Assert.assertEquals(2, character.getX());
        Assert.assertEquals(0, character.getY());

        bubble.tick();
        Assert.assertEquals(2, character.getX());
        Assert.assertEquals(1, character.getY());

        bubble.tick();
        Assert.assertEquals(2, character.getX());
        Assert.assertEquals(2, character.getY());

        bubble.tick();
        Assert.assertEquals(1, character.getX());
        Assert.assertEquals(2, character.getY());

        bubble.tick();
        Assert.assertEquals(0, character.getX());
        Assert.assertEquals(2, character.getY());

        bubble.tick();
        Assert.assertEquals(0, character.getX());
        Assert.assertEquals(1, character.getY());

        bubble.tick();
        Assert.assertEquals(0, character.getX());
        Assert.assertEquals(0, character.getY());
    }

    @Test
    public void testMovingTogether() {
        MapTile[][] map = {
                {
                        new SlopeMapTile(Direction.EAST, Decoration.GRASS),
                        new SlopeMapTile(Direction.SOUTH, Decoration.GRASS),
                        new SlopeMapTile(Direction.SOUTH, Decoration.GRASS),
                },
                {
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                },
                {
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                },
        };
        World world = new World(map, Weather.SUNNY);
        Human character = new Human(world, 0, 0, "Зафод Библброкс", new Scarf(Color.ORANGE));
        Transport bubble = new Transport(world, 0, 0, "Пузырь", 1);
        character.getIntoTransport(bubble);

        bubble.tick();
        bubble.tick();
        bubble.tick();
        bubble.tick();
        bubble.tick();

        Assert.assertEquals(bubble.getX(), character.getX());
        Assert.assertEquals(bubble.getY(), character.getY());
    }

    @Test
    public void testScript() {
        MapTile[][] map = {
                {
                        new SlopeMapTile(Direction.EAST, Decoration.GRASS),
                        new SlopeMapTile(Direction.EAST, Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new SlopeMapTile(Direction.WEST, Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new SlopeMapTile(Direction.EAST, Decoration.FENCED_BRIDGE),
                        new FlatMapTile(Decoration.FENCED_BRIDGE),
                        new SlopeMapTile(Direction.EAST, Decoration.PLATFORM),
                        new FlatMapTile(Decoration.PLATFORM),
                        new FlatMapTile(Decoration.PLATFORM), // 9
                        new SlopeMapTile(Direction.WEST, Decoration.PLATFORM),
                },
        };
        World world = new World(map, Weather.SUNNY);
        Applause applause = Applause.NO_APPLAUSE;

        Human[] people = new Human[101];
        int goalX = 9;
        int goalY = 0;
        for (int i = 0; i < people.length; i++) {
            people[i] = new Human(world, goalX, goalY, "Зритель", new Scarf(Color.EMERALD));
        }
        Crowd crowd = new Crowd(people);

        Human character = new Human(world, 0, 0, "Зафод Библброкс", new Scarf(Color.ORANGE));
        Transport bubble = new Transport(world, 0, 0, "Пузырь", 1);

        character.getIntoTransport(bubble);
        int lastCharacterX = -1;
        while (lastCharacterX != character.getX()) {
            lastCharacterX = character.getX();
            bubble.tick();
        }
        if (map[character.getY()][character.getX()].decoration == Decoration.PLATFORM) {
            applause = crowd.applaud();
        }

        Assert.assertEquals(Applause.LOUD_APPLAUSE, applause);
        character.getOutOfTransport();

        Assert.assertTrue(character.isScarfShining());
    }

    @Test
    public void testAgainstScript() {
        MapTile[][] map = {
                {
                        new SlopeMapTile(Direction.EAST, Decoration.GRASS),
                        new SlopeMapTile(Direction.SOUTH, Decoration.GRASS),
                        new SlopeMapTile(Direction.SOUTH, Decoration.GRASS),
                        new SlopeMapTile(Direction.SOUTH, Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new SlopeMapTile(Direction.EAST, Decoration.FENCED_BRIDGE),
                        new FlatMapTile(Decoration.FENCED_BRIDGE),
                        new SlopeMapTile(Direction.EAST, Decoration.PLATFORM),
                        new FlatMapTile(Decoration.PLATFORM),
                        new FlatMapTile(Decoration.PLATFORM), // 9
                        new SlopeMapTile(Direction.WEST, Decoration.PLATFORM),
                },
                {
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                },
                {
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                        new FlatMapTile(Decoration.GRASS),
                },
        };
        World world = new World(map, Weather.SUNNY);
        Applause applause = Applause.NO_APPLAUSE;

        Human[] people = new Human[101];
        int goalX = 9;
        int goalY = 0;
        for (int i = 0; i < people.length; i++) {
            people[i] = new Human(world, goalX, goalY, "Зритель", new Scarf(Color.EMERALD));
        }
        Crowd crowd = new Crowd(people);

        Human character = new Human(world, 0, 0, "Зафод Библброкс", new Scarf(Color.BLUE));
        Transport bubble = new Transport(world, 0, 0, "Пузырь", 1);

        character.getIntoTransport(bubble);
        int lastCharacterX = -1;
        while (lastCharacterX != character.getX()) {
            lastCharacterX = character.getX();
            bubble.tick();
        }
        if (map[character.getY()][character.getX()].decoration == Decoration.PLATFORM) {
            applause = crowd.applaud();
        }

        Assert.assertEquals(Applause.NO_APPLAUSE, applause);
        character.getOutOfTransport();

        Assert.assertFalse(character.isScarfShining());
    }
}
