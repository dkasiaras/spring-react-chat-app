package com.dimitris.kasiaras.actionmonitor.adapter.websocket.controller;

import com.dimitris.kasiaras.actionmonitor.adapter.converter.JsonConverter;
import com.dimitris.kasiaras.actionmonitor.domain.fixtures.MessageFixture;
import com.dimitris.kasiaras.actionmonitor.domain.service.Messenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class ChatControllerTest {

  @Mock
  private Messenger messenger;
  private ChatController chatController;

  @BeforeEach
  void setUp() {
    this.chatController = new ChatController(messenger);
  }

  @Test
  void whenReceivePublicMessage_ThenSendMessageWithMessenger() {
    var message = MessageFixture.getPublicMessage();
    this.chatController.receivePublicMessage(message);
    verify(this.messenger).sendMessage(JsonConverter.convertToJsonString(message));
    verifyNoMoreInteractions(this.messenger);
  }

  @Test
  void whenReceivePrivateMessage_ThenSendMessageWithMessenger() {
    var message = MessageFixture.getPrivateMessage();
    this.chatController.receivePrivateMessage(message);
    verify(this.messenger).sendMessage(JsonConverter.convertToJsonString(message));
    verifyNoMoreInteractions(this.messenger);
  }
}