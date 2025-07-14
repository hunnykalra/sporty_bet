package com.sporty.bet.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.context.annotation.Bean;

@ConcreteProxy
public class RocketMqConfiguration {

    @Bean
    public RocketMQTemplate rocketMQTemplate() {
        RocketMQTemplate template = new RocketMQTemplate();
        DefaultMQProducer producer = new DefaultMQProducer("your-producer-group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setRetryTimesWhenSendFailed(3);
        try {
            producer.start();
        } catch (MQClientException e) {
            throw new RuntimeException("Failed to start RocketMQ producer", e);
        }
        template.setProducer(producer);
        return template;
    }

}

