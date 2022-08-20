package com.dimitris.kasiaras.actionmonitor.adapter.storage.entity;

import com.dimitris.kasiaras.actionmonitor.domain.model.Message;
import com.dimitris.kasiaras.actionmonitor.domain.model.Status;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MessageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String senderName;
  private String receiverName;
  private String message;
  private String date;

  @Enumerated(EnumType.STRING)
  private Status status;

  public static MessageEntity fromDomain(Message message) {
    MessageEntity messageEntity = new MessageEntity();
    messageEntity.setSenderName(message.getSenderName());
    messageEntity.setMessage(message.getMessage());
    messageEntity.setReceiverName(message.getReceiverName());
    messageEntity.setDate(message.getDate());
    messageEntity.setStatus(message.getStatus());
    return messageEntity;
  }

}
