package com.sporty.bet.rocketmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RocketMQProducerService {
    private final RocketMQTemplate rocketMQTemplate;

    public void sendBetSettlementMessage(String message) {
        rocketMQTemplate.send("bet-settlements", MessageBuilder.withPayload(message).build());
        log.info("Sent message to RocketMQ topic 'bet-settlements': {}", message);
    }
} 