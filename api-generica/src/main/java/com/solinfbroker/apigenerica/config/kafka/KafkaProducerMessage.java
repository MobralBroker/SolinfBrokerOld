package com.solinfbroker.apigenerica.config.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerMessage {

    private final Logger LOG = LoggerFactory.getLogger(KafkaProducerMessage.class);

    @Autowired
    private KafkaAdminConfig kafkaAdminConfig;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Object message, String topic){
        LOG.info("PRODUCER sent with Kafka ::: {}", message);
        kafkaTemplate.send(topic, message);
    }

}
