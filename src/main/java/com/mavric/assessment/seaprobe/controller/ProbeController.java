package com.mavric.assessment.seaprobe.controller;

import com.mavric.assessment.seaprobe.model.*;
import com.mavric.assessment.seaprobe.service.ProbeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/probe")
public class ProbeController {

    private final ProbeService probeService;

    public ProbeController(ProbeService probeService) {
        this.probeService = probeService;
    }

    @PostMapping("/init")
    public ResponseEntity<String> initialize(@RequestBody InitRequest request) {
        probeService.initialize(
                request.width,
                request.height,
                request.startPosition,
                request.direction,
                request.obstacles
        );
        return ResponseEntity.ok("Probe initialized");
    }

    @PostMapping("/command")
    public ResponseEntity<String> executeCommands(@RequestBody List<Command> commands) {
        probeService.executeCommands(commands);
        return ResponseEntity.ok("Commands executed");
    }

    @GetMapping("/status")
    public ProbeStatus getStatus() {
        return new ProbeStatus(
                probeService.getCurrentPosition(),
                probeService.getCurrentDirection(),
                probeService.getVisitedPositions()
        );
    }

    @Data
    static class InitRequest {
        int width;
        int height;
        Position startPosition;
        Direction direction;
        List<Position> obstacles;
    }

    static class ProbeStatus {
        Position currentPosition;
        Direction direction;
        Set<Position> visitedPositions;

        public ProbeStatus(Position currentPosition, Direction direction, Set<Position> visitedPositions) {
            this.currentPosition = currentPosition;
            this.direction = direction;
            this.visitedPositions = visitedPositions;
        }

        // Add getters (optional, or use @Getter)
        public Position getCurrentPosition() {
            return currentPosition;
        }

        public Direction getDirection() {
            return direction;
        }

        public Set<Position> getVisitedPositions() {
            return visitedPositions;
        }
    }
}

