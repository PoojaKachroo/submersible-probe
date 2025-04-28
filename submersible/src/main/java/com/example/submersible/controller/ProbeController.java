package com.example.submersible.controller;

import com.example.submersible.model.Direction;
import com.example.submersible.model.OceanGrid;
import com.example.submersible.model.Probe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/probe")
public class ProbeController {

    private final Probe probe;

    public ProbeController() {
        OceanGrid grid = new OceanGrid(5, 5);
        grid.addObstacle(2, 2);
        this.probe = new Probe(0, 0, Direction.NORTH, grid);
    }

    @PostMapping("/move")
    public ResponseEntity<?> move(@RequestBody MoveRequest moveRequest) {
        probe.processCommands(moveRequest.getCommands());
        Map<String, Object> response = new HashMap<>();
        response.put("position", Map.of("x", probe.getX(), "y", probe.getY()));
        response.put("visited", probe.getVisitedCoordinates());
        return ResponseEntity.ok(response);
    }

    static class MoveRequest {
        private String commands;
        public String getCommands() { return commands; }
        public void setCommands(String commands) { this.commands = commands; }
    }
}

