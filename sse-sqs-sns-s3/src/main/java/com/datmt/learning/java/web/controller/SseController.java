package com.datmt.learning.java.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class SseController {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    @GetMapping("/sse")
    public SseEmitter stream(Authentication authentication) {
        String username = authentication.getName();
        SseEmitter emitter = new SseEmitter(0L); // No timeout
        emitters.put(username, emitter);
        emitter.onCompletion(() -> emitters.remove(username));
        emitter.onTimeout(() -> emitters.remove(username));
        return emitter;
    }

    public void sendToUser(String username, String presignedUrl) {
        SseEmitter emitter = emitters.get(username);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().data(presignedUrl));
            } catch (IOException e) {
                emitters.remove(username);
            }
        }
    }
}
