package com.dimitris.kasiaras.actionmonitor.adapter.messaging.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaVariables {
  public static final String TOPIC = "topic";
  public static final String GROUP = "my_group_id";
}
