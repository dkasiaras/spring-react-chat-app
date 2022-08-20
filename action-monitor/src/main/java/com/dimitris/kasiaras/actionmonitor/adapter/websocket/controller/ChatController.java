package com.dimitris.kasiaras.actionmonitor.adapter.websocket.controller;

import com.dimitris.kasiaras.actionmonitor.adapter.converter.JsonConverter;
import com.dimitris.kasiaras.actionmonitor.domain.model.Message;
import com.dimitris.kasiaras.actionmonitor.domain.service.Messenger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@AllArgsConstructor
public class ChatController {
  private Messenger messenger;

  @MessageMapping("/message")
  public void receivePublicMessage(@Payload Message message) {
    this.messenger.sendMessage(JsonConverter.convertToJsonString(message));
  }

  @MessageMapping("/private-message")
  public void receivePrivateMessage(@Payload Message message) {
    messenger.sendMessage(JsonConverter.convertToJsonString(message));
  }
}
