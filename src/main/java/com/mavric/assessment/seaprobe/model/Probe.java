package com.mavric.assessment.seaprobe.model;

import com.mavric.assessment.seaprobe.exception.ObstacleEncounteredException;
import com.mavric.assessment.seaprobe.exception.OutOfBoundsException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Probe {
    private Position position;
    private Direction direction;
    private final Set<Position> visited = new HashSet<>();

    public Probe(Position startPos, Direction startDir) {
        this.position = startPos;
        this.direction = startDir;
        visited.add(new Position(startPos.getX(), startPos.getY()));
    }

    // ✅ Getters (manually written)
    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public Set<Position> getVisited() {
        return new HashSet<>(visited); // Defensive copy
    }

    public void executeCommand(Command cmd, Grid grid) {
        switch (cmd) {
            case MOVE_FORWARD -> move(1, grid);
            case MOVE_BACKWARD -> move(-1, grid);
            case TURN_LEFT -> direction = direction.turnLeft();
            case TURN_RIGHT -> direction = direction.turnRight();
        }
    }

    private void move(int step, Grid grid) {
        int x = position.getX();
        int y = position.getY();
        switch (direction) {
            case NORTH -> y += step;
            case SOUTH -> y -= step;
            case EAST -> x += step;
            case WEST -> x -= step;
        }

        Position newPos = new Position(x, y);

        if (!grid.isInsideGrid(newPos)) {
            throw new OutOfBoundsException("Move outside grid: " + newPos);
        }
        if (grid.isObstacle(newPos)) {
            throw new ObstacleEncounteredException("Obstacle at: " + newPos);
        }

        position = newPos;
        visited.add(new Position(x, y));
    }

    @Override
    public String toString() {
        return "Probe{" +
                "position=" + position +
                ", direction=" + direction +
                ", visited=" + visited +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Probe)) return false;
        Probe probe = (Probe) o;
        return Objects.equals(position, probe.position) &&
                direction == probe.direction &&
                Objects.equals(visited, probe.visited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction, visited);
    }
}
