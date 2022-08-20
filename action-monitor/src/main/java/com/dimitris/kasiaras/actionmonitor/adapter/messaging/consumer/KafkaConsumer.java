package com.dimitris.kasiaras.actionmonitor.adapter.messaging.consumer;

import com.dimitris.kasiaras.actionmonitor.adapter.converter.JsonConverter;
import com.dimitris.kasiaras.actionmonitor.adapter.storage.entity.MessageEntity;
import com.dimitris.kasiaras.actionmonitor.adapter.storage.reporitory.MessageRepository;
import com.dimitris.kasiaras.actionmonitor.domain.model.Message;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

import static com.dimitris.kasiaras.actionmonitor.adapter.messaging.configuration.KafkaVariables.GROUP;
import static com.dimitris.kasiaras.actionmonitor.adapter.messaging.configuration.KafkaVariables.TOPIC;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumer {

  private final SimpMessagingTemplate messagingTemplate;
  private final MessageRepository messageRepository;

  @KafkaListener(topics = TOPIC, groupId = GROUP)
  public void getMessage(String message) {
    log.debug("Message received {}.", message);
    Message msg = JsonConverter.getMessageFromString(message);
    var savedMessage = messageRepository.save(MessageEntity.fromDomain(msg));

    if (StringUtils.isNotEmpty(msg.getReceiverName())) {
      this.messagingTemplate.convertAndSend(
              MessageFormat.format("/user/{0}/private", msg.getReceiverName()), msg);

      log.info("Public message {} sent to user {}", savedMessage, msg.getReceiverName());
    } else {

      this.messagingTemplate.convertAndSend("/topic/public", msg);
      log.info("Public message {} sent", savedMessage);
    }

    this.messagingTemplate.convertAndSend("/topic/admin", msg);
  }
}
