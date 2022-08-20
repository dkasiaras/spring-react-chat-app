package com.dimitris.kasiaras.actionmonitor.domain.fixtures;

import com.dimitris.kasiaras.actionmonitor.domain.model.Message;
import com.dimitris.kasiaras.actionmonitor.domain.model.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageFixture {

  public static Message getPublicMessage() {
    Message message = new Message();
    message.setDate("2022-08-20T12:00:00");
    message.setStatus(Status.MESSAGE);
    message.setSenderName("harry.potter");
    message.setMessage("Hello Topic");
    return message;
  }

  public static Message getPrivateMessage() {
    Message message = new Message();
    message.setDate("2022-08-20T12:00:00");
    message.setStatus(Status.MESSAGE);
    message.setSenderName("harry.potter");
    message.setReceiverName("ron.weasley");
    message.setMessage("Hello Topic");
    return message;
  }
}
