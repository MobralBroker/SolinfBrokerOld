package com.solinfbroker.apigenerica.service;

import com.solinfbroker.apigenerica.config.kafka.Topicos;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    private final Logger LOG = LoggerFactory.getLogger(KafkaListener.class);

    @org.springframework.kafka.annotation.KafkaListener(topics = Topicos.TOPIC_ATUALIZACAO_MERCADO,  groupId = Topicos.TOPIC_ATUALIZACAO_MERCADO)
    public void listeningAtualizacaoMercado(ConsumerRecord<String, String> record)  {
        LOG.info("CONSUMER message from Kafka: {}", record.value());

        /* Business rule code with message */
    }

    @SneakyThrows
    @org.springframework.kafka.annotation.KafkaListener(topics = Topicos.TOPIC_INFO_USUARIO,  groupId = Topicos.TOPIC_INFO_USUARIO)
    public void listeningInfoUsuario(ConsumerRecord<String, String> record)  {
        LOG.info("CONSUMER message from Kafka: {}", record.value());

        /* Business rule code with message */
    }

    @SneakyThrows
    @org.springframework.kafka.annotation.KafkaListener(topics = Topicos.TOPIC_CHECK_SAUDE,  groupId = Topicos.TOPIC_CHECK_SAUDE)
    public void listeningSaudeCheck(ConsumerRecord<String, String> record)  {
        LOG.info("CONSUMER message from Kafka: {}", record.value());

        /* Business rule code with message */
    }

    @SneakyThrows
    @org.springframework.kafka.annotation.KafkaListener(topics = Topicos.TOPIC_ATUALIZACAO_TESTES,  groupId = Topicos.TOPIC_ATUALIZACAO_TESTES)
    public void listeningAtualizacaoTeste(ConsumerRecord<String, String> record)  {
        LOG.info("CONSUMER message from Kafka: {}", record.value());

        /* Business rule code with message */
    }


}

