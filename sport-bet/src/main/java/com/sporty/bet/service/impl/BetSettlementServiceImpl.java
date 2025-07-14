package com.sporty.bet.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sporty.bet.entity.Bet;
import com.sporty.bet.repository.BetRepository;
import com.sporty.bet.request.SportsEventOutcome;
import com.sporty.bet.rocketmq.RocketMQProducerService;
import com.sporty.bet.service.BetSettlementService;
import com.sporty.bet.exception.SportyBetException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BetSettlementServiceImpl implements BetSettlementService {
    private final BetRepository betRepository;
    private final RocketMQProducerService rocketMQProducerService;
    private final ObjectMapper objectMapper;

    @Override
    public void settlementBet(SportsEventOutcome sportsEventOutcome) throws SportyBetException {
        if (sportsEventOutcome == null || sportsEventOutcome.getEventId() == null) {
            log.warn("Invalid event outcome or eventId: {}", sportsEventOutcome);
            return;
        }

        List<Bet> bets = betRepository.findByEventId(sportsEventOutcome.getEventId());
        if (bets.isEmpty()) {
            log.info("No bets found for eventId: {}", sportsEventOutcome.getEventId());
            bets.add(Bet.builder().betId("12").betAmount(new BigDecimal(1200)).build());
            bets.add(Bet.builder().betId("15").betAmount(new BigDecimal(1200)).build());
           // return;
        }

        String json = serializeBets(bets);
        rocketMQProducerService.sendBetSettlementMessage(json);
        log.info("Sent settled bets to RocketMQ: {}", json);
    }

    private String serializeBets(List<Bet> bets) throws SportyBetException {
        try {
            return objectMapper.writeValueAsString(bets);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize bets for settlement: {}", bets, e);
            throw new SportyBetException("Failed to serialize bets for settlement", e);
        }
    }
} 