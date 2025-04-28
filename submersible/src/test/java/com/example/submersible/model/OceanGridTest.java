package com.example.submersible.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OceanGridTest {

    @Test
    void testPreventMovingOutsideNorthBoundary() {
        OceanGrid grid = new OceanGrid(2, 2);
        Probe probe = new Probe(1, 1, Direction.NORTH, grid);

        probe.processCommands("F");
        assertEquals(1, probe.getX());
        assertEquals(1, probe.getY());
    }

    @Test
    void testPreventMovingOutsideSouthBoundary() {
        OceanGrid grid = new OceanGrid(2, 2);
        Probe probe = new Probe(0, 0, Direction.SOUTH, grid);

        probe.processCommands("F");
        assertEquals(0, probe.getX());
        assertEquals(0, probe.getY());
    }

    @Test
    void testPreventMovingOutsideWestBoundary() {
        OceanGrid grid = new OceanGrid(2, 2);
        Probe probe = new Probe(0, 0, Direction.WEST, grid);

        probe.processCommands("F");
        assertEquals(0, probe.getX());
        assertEquals(0, probe.getY());
    }

    @Test
    void testPreventMovingOutsideEastBoundary() {
        OceanGrid grid = new OceanGrid(2, 2);
        Probe probe = new Probe(2, 2, Direction.EAST, grid);

        probe.processCommands("F");
        assertEquals(2, probe.getX());
        assertEquals(2, probe.getY());
    }

}
