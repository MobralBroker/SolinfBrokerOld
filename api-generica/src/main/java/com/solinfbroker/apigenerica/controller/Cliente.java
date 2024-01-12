package com.solinfbroker.apigenerica.controller;

import com.solinfbroker.apigenerica.config.kafka.Topicos;
import com.solinfbroker.apigenerica.config.kafka.KafkaProducerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cliente")
public class Cliente {

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @GetMapping
    private ResponseEntity teste(){
        kafkaProducerMessage.sendMessage("teste", Topicos.TOPIC_ATUALIZACAO_TESTES);
        return ResponseEntity.ok("asd");
    }
}
