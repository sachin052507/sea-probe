package com.mavric.assessment.seaprobe.service;

import com.mavric.assessment.seaprobe.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProbeServiceTest {

    ProbeService service;

    @BeforeEach
    void setup() {
        service = new ProbeService();
        service.initialize(5, 5, new Position(2, 2), Direction.NORTH, List.of());
    }

    @Test
    void testMoveForward() {
        service.executeCommands(List.of(Command.MOVE_FORWARD));
        assertEquals(new Position(2, 3), service.getCurrentPosition());
        assertEquals(Direction.NORTH, service.getCurrentDirection());
    }

    @Test
    void testTurnLeft() {
        service.executeCommands(List.of(Command.TURN_LEFT));
        assertEquals(Direction.WEST, service.getCurrentDirection());
    }

    @Test
    void testVisitedTracking() {
        service.executeCommands(List.of(Command.MOVE_FORWARD, Command.TURN_RIGHT, Command.MOVE_FORWARD));
        assertTrue(service.getVisitedPositions().contains(new Position(2,3)));
        assertTrue(service.getVisitedPositions().contains(new Position(3,3)));
    }
}
