package com.mavric.assessment.seaprobe.service;

import com.mavric.assessment.seaprobe.model.*;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Getter
public class ProbeService {
    private Probe probe;
    private Grid grid;

    public void initialize(int width, int height, Position start, Direction direction, List<Position> obstacles) {
        grid = new Grid(width, height, Set.copyOf(obstacles));
        probe = new Probe(start, direction);
    }

    public void executeCommands(List<Command> commands) {
        commands.forEach(cmd -> probe.executeCommand(cmd, grid));
    }

    public Position getCurrentPosition() {
        return probe.getPosition();
    }

    public Set<Position> getVisitedPositions() {
        return probe.getVisited();
    }

    public Direction getCurrentDirection() {
        return probe.getDirection();
    }
}
