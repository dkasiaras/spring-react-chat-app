package com.dimitris.kasiaras.actionmonitor.adapter.messaging.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static com.dimitris.kasiaras.actionmonitor.adapter.messaging.configuration.KafkaVariables.TOPIC;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class KafkaProducerTest {

  @Mock private KafkaTemplate<String, String> kafkaTemplate;
  private KafkaProducer kafkaProducer;

  @BeforeEach
  void setUp() {
    this.kafkaProducer = new KafkaProducer(kafkaTemplate);
  }

  @Test
  void whenSendIsCalled_theMessageIsSendWithKafkaTemplate() {
    String message = "hello";
    this.kafkaProducer.sendMessage(message);
    verify(this.kafkaTemplate).send(TOPIC, message);
    verifyNoMoreInteractions(this.kafkaTemplate);
  }
}