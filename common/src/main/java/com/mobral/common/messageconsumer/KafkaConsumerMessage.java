package com.mobral.common.messageconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component
public class KafkaConsumerMessage {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @KafkaListener(topics = Constans.KAKFA_TOPIC_EXAMPLE1, groupId = Constans.KAFKA_GROUD_ID)
    public void listening1(ConsumerRecord<String, String> record) {
        LOG.info("Message sent with Kafka ::: {}", record.value());

        /* Business rule code with message */

    }

    @KafkaListener(topics = Constans.KAKFA_TOPIC_EXAMPLE1,  groupId = Constans.KAFKA_GROUD_ID2)
    public void listening2(ConsumerRecord<String, String> record) {
        LOG.info("Received message from Kafka: {}", record.value());

        /* Business rule code with message */

    }


}

