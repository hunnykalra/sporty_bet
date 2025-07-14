package com.sporty.bet.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sporty.bet.exception.SportyBetException;
import com.sporty.bet.request.SportsEventOutcome;
import com.sporty.bet.service.BetSettlementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BetSettlementService betSettlementService;

    @KafkaListener(topics = "event-outcomes", groupId = "sport-bet-group")
    public void listenEventOutcomes(String message) {
        try {
            SportsEventOutcome outcome = objectMapper.readValue(message, SportsEventOutcome.class);
            betSettlementService.settlementBet(outcome);
            log.info("Received event outcome: eventId={}, eventName={}, eventWinnerId={}",
                    outcome.getEventId(), outcome.getEventName(), outcome.getEventWinnerId());
        } catch (Exception exception) {
            log.error("Failed to deserialize event outcome message: {}", message, exception);
            throw new SportyBetException("Failed to deserialize event outcome message", exception);
        }
    }
} 