package com.example.submersible.model;

import java.util.ArrayList;
import java.util.List;

public class Probe {
    private int x;
    private int y;
    private Direction direction;
    private OceanGrid grid;
    private List<String> visitedCoordinates = new ArrayList<>();

    public Probe(int x, int y, Direction direction, OceanGrid grid) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid = grid;
        visitedCoordinates.add(x + "," + y);
    }

    public void processCommands(String commands) {
        for (char c : commands.toCharArray()) {
            switch (c) {
                case 'F':
                    moveForward();
                    break;
                case 'B':
                    moveBackward();
                    break;
                case 'L':
                    turnLeft();
                    break;
                case 'R':
                    turnRight();
                    break;
            }
        }
    }

    private void moveForward() {
        //To-Do
    }

    private void moveBackward() {
        //To-Do
    }

    private void turnLeft() {
        direction = direction.left();
    }

    private void turnRight() {
        direction = direction.right();
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public List<String> getVisitedCoordinates() { return visitedCoordinates; }

}
