package com.solinfbroker.apigenerica.config.kafka;


import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;

@Configuration
public class KafkaAdminConfig {
    @Autowired
    private KafkaProperties properties;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        var configs = new HashMap<String, Object>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        return new KafkaAdmin(configs);
    }

    @Bean
    public KafkaAdmin.NewTopics newTopics() {

        return new KafkaAdmin.NewTopics(
                TopicBuilder.name(Topicos.TOPIC_ATUALIZACAO_MERCADO).partitions(1).build(),
                TopicBuilder.name(Topicos.TOPIC_CHECK_SAUDE).partitions(1).build(),
                TopicBuilder.name(Topicos.TOPIC_INFO_USUARIO).partitions(1).build(),
                TopicBuilder.name(Topicos.TOPIC_ATUALIZACAO_TESTES).partitions(1).build()
                );
    }

}
