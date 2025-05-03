package com.mavric.assessment.seaprobe.model;

import java.util.HashSet;
import java.util.Set;

public class Grid {
    private final int width;
    private final int height;
    private final Set<Position> obstacles;

    public Grid(int width, int height, Set<Position> obstacles) {
        this.width = width;
        this.height = height;
        // If obstacles is null, initialize as empty HashSet (safe)
        this.obstacles = (obstacles != null) ? new HashSet<>(obstacles) : new HashSet<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Set<Position> getObstacles() {
        return new HashSet<>(obstacles);  // Defensive copy (optional but safe)
    }

    public boolean isInsideGrid(Position pos) {
        return pos.getX() >= 0 && pos.getX() < width &&
                pos.getY() >= 0 && pos.getY() < height;
    }

    public boolean isObstacle(Position pos) {
        return obstacles.contains(pos);
    }
}
