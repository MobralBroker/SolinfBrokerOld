package com.mobral.common.messageproducer;

import com.mobral.common.messageconsumer.KafkaConsumerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerMessage {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(){
        LOG.info("Message sent with Kafka ::: {}", Object.class);

        kafkaTemplate.send(Constans.KAKFA_TOPIC_EXAMPLE1,Object.class);
    }

}
