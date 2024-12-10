package org.example.kafka_partition.controller;

import lombok.RequiredArgsConstructor;

import org.example.kafka_partition.service.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/send")
    public String sendEvent(
            @RequestParam String eventType,
            @RequestParam String eventData) {
        eventService.sendEvent(eventType, eventData);
        return "success";
    }
}
