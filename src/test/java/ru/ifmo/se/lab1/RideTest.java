package ru.ifmo.se.lab1;

import org.junit.Assert;
import org.junit.Test;
import ru.ifmo.se.lab1.domain.*;
import ru.ifmo.se.lab1.domain.world.*;

public class RideTest {

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
