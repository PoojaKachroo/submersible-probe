package com.example.submersible.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProbeTest {

    @Test
    void testProbeMovesForward() {
        OceanGrid grid = new OceanGrid(3, 3);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.processCommands("F");

        assertEquals(0, probe.getX());
        assertEquals(1, probe.getY());
    }

    @Test
    void testMoveBackwardBlockedAtGridBottomBoundary() {
        OceanGrid grid = new OceanGrid(3, 3);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.processCommands("B");

        assertEquals(0, probe.getX());
        assertEquals(0, probe.getY());
    }

    @Test
    void testBlockedByObstacleOnForwardMove() {
        OceanGrid grid = new OceanGrid(5, 5);
        grid.addObstacle(1, 2);
        Probe probe = new Probe(1, 1, Direction.NORTH, grid);

        probe.processCommands("F");

        assertEquals(1, probe.getX());
        assertEquals(1, probe.getY());
    }

    @Test
    void testVisitedCoordinatesLogging() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.processCommands("FRF");

        List<String> expectedPath = List.of("0,0", "0,1", "1,1");
        assertEquals(expectedPath, probe.getVisitedCoordinates());
    }
}
