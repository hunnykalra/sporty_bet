package com.sporty.bet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sporty.bet.kafka.KafkaProducerService;
import com.sporty.bet.request.SportsEventOutcome;

@RestController
@RequestMapping("/api/events")
public class SportsEventOutcomeController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public SportsEventOutcomeController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    /**
     * Publishes a sports event outcome to Kafka topic 'event-outcomes'.
     * @param outcome The event outcome (eventId, eventName, eventWinnerId)
     * @return HTTP 200 if published successfully
     */
    @PostMapping("/outcome")
    public ResponseEntity<String> publishEventOutcome(@RequestBody SportsEventOutcome outcome) {
        kafkaProducerService.sendEventOutcome(outcome);
        return ResponseEntity.ok("Event outcome published to Kafka topic 'event-outcomes'.");
    }
} 