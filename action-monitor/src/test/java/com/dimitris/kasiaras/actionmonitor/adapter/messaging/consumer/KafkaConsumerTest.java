package com.dimitris.kasiaras.actionmonitor.adapter.messaging.consumer;


import com.dimitris.kasiaras.actionmonitor.adapter.converter.JsonConverter;
import com.dimitris.kasiaras.actionmonitor.adapter.storage.entity.MessageEntity;
import com.dimitris.kasiaras.actionmonitor.adapter.storage.reporitory.MessageRepository;
import com.dimitris.kasiaras.actionmonitor.domain.fixtures.MessageFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class KafkaConsumerTest {

  @Mock
  private SimpMessagingTemplate messagingTemplate;
  @Mock
  private MessageRepository messageRepository;

  private KafkaConsumer kafkaConsumer;

  @BeforeEach
  void setUp() {
    this.kafkaConsumer = new KafkaConsumer(this.messagingTemplate, this.messageRepository);
  }

  @Test
  void whenPublicMessageReceived_thenMessageIsSentToPublicTopic() {
    var expectedMessage = MessageFixture.getPublicMessage();
    var expectedEntity = new MessageEntity(null, expectedMessage.getSenderName(),
            expectedMessage.getReceiverName(),
            expectedMessage.getMessage(),
            expectedMessage.getDate(),
            expectedMessage.getStatus());

    String message = JsonConverter.convertToJsonString(expectedMessage);


    this.kafkaConsumer.getMessage(message);

    verify(this.messageRepository).save(expectedEntity);
    verify(this.messagingTemplate).convertAndSend("/topic/public", expectedMessage);
    verify(this.messagingTemplate).convertAndSend("/topic/admin", expectedMessage);
    verifyNoMoreInteractions(this.messagingTemplate,  this.messageRepository);
  }

  @Test
  void whenPrivateMessageReceived_thenMessageIsSentToPrivateUser() {
    var expectedMessage = MessageFixture.getPrivateMessage();
    var expectedEntity = new MessageEntity(null, expectedMessage.getSenderName(),
            expectedMessage.getReceiverName(),
            expectedMessage.getMessage(),
            expectedMessage.getDate(),
            expectedMessage.getStatus());

    String message = JsonConverter.convertToJsonString(expectedMessage);


    this.kafkaConsumer.getMessage(message);

    verify(this.messageRepository).save(expectedEntity);
    verify(this.messagingTemplate).convertAndSend("/user/ron.weasley/private", expectedMessage);
    verify(this.messagingTemplate).convertAndSend("/topic/admin", expectedMessage);
    verifyNoMoreInteractions(this.messagingTemplate,  this.messageRepository);
  }
}