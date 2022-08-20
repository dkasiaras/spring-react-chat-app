package com.dimitris.kasiaras.actionmonitor.adapter.messaging.producer;

import com.dimitris.kasiaras.actionmonitor.domain.service.Messenger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.dimitris.kasiaras.actionmonitor.adapter.messaging.configuration.KafkaVariables.TOPIC;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaProducer implements Messenger {
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String message) {
    log.debug("Sending message to kafka topic {}" + message);
    this.kafkaTemplate.send(TOPIC, message);
  }
}
