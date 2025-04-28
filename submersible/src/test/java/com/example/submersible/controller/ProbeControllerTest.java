package com.example.submersible.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProbeController.class)
public class ProbeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldMoveProbeSuccessfully() throws Exception {

        mockMvc.perform(post("/probe/move")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"commands\": \"FFRFF\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position.x").exists())
                .andExpect(jsonPath("$.position.y").exists())
                .andExpect(jsonPath("$.direction").exists());
    }

    @Test
    void shouldHandleInvalidCommand() throws Exception {
        mockMvc.perform(post("/probe/move")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"commands\": \"ABC\" }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldHandleEmptyCommandString() throws Exception {
        mockMvc.perform(post("/probe/move")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"commands\": \"\" }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldPreventMovingOutsideBoundary() throws Exception {
        mockMvc.perform(post("/probe/move")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"commands\": \"FFFFFFFFF\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position.x").exists())
                .andExpect(jsonPath("$.position.y").value(5));
    }

    @Test
    void shouldAvoidObstacleDuringMovement() throws Exception {
        mockMvc.perform(post("/probe/move")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"commands\": \"FRF\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.obstacleDetected").value(true));
    }

    @Test
    void shouldHandleMissingCommandsField() throws Exception {
        mockMvc.perform(post("/probe/move")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ }"))
                .andExpect(status().isBadRequest());
    }
}
