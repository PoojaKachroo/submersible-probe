package com.example.submersible.model;

import java.util.HashSet;
import java.util.Set;

public class OceanGrid {

    private final int width;
    private final int height;
    private final Set<String> obstacles = new HashSet<>();

    public OceanGrid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isInsideGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public boolean hasObstacle(int x, int y) {
        return obstacles.contains(x + "," + y);
    }

    public void addObstacle(int x, int y) {
        obstacles.add(x + "," + y);
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

}
