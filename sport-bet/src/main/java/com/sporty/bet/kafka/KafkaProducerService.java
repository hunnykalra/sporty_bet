package com.sporty.bet.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sporty.bet.exception.SportyBetException;
import com.sporty.bet.request.SportsEventOutcome;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendEventOutcome(SportsEventOutcome outcome) {
        try {
            String message = objectMapper.writeValueAsString(outcome);
            kafkaTemplate.send("event-outcomes", message);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize event outcome: {}", outcome, e);
            throw new SportyBetException("Failed to serialize event outcome", e);
        }
    }
} 