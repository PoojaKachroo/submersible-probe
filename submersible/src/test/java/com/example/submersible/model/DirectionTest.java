package com.example.submersible.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {

    @Test
    void testTurnLeftFromNorth() {
        assertEquals(Direction.WEST, Direction.NORTH.turnLeft());
    }

    @Test
    void testTurnLeftFromSouth() {
        assertEquals(Direction.EAST, Direction.SOUTH.turnLeft());
    }

    @Test
    void testTurnRightFromEast() {
        assertEquals(Direction.SOUTH, Direction.EAST.turnRight());
    }

    @Test
    void testTurnRightFromNorth() {
        assertEquals(Direction.EAST, Direction.NORTH.turnRight());
    }
}
