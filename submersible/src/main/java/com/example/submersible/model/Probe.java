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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean processCommands(String commands) {
        boolean obstacleDetected = false;

        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'F':
                    obstacleDetected = moveForward() || obstacleDetected;
                    break;
                case 'B':
                    obstacleDetected = moveBackward() || obstacleDetected;
                    break;
                case 'L':
                    turnLeft();
                    break;
                case 'R':
                    turnRight();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command: " + command);
            }
        }
        return obstacleDetected;
    }

    private boolean moveForward() {
        int newX = x + direction.getDx();
        int newY = y + direction.getDy();
        if (grid.isInsideGrid(newX, newY)) {
            if (!grid.hasObstacle(newX, newY)) {
                x = newX;
                y = newY;
                visitedCoordinates.add(x + "," + y);
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean moveBackward() {
        int newX = x - direction.getDx();
        int newY = y - direction.getDy();
        if (grid.isInsideGrid(newX, newY)) {
            if (!grid.hasObstacle(newX, newY)) {
                x = newX;
                y = newY;
                visitedCoordinates.add(x + "," + y);
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private void turnLeft() {
        direction = direction.turnLeft();
    }

    private void turnRight() {
        direction = direction.turnRight();
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public List<String> getVisitedCoordinates() { return visitedCoordinates; }

}
