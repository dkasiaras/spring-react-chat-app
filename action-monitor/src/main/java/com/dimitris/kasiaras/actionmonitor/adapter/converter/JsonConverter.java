package com.dimitris.kasiaras.actionmonitor.adapter.converter;

import com.dimitris.kasiaras.actionmonitor.domain.model.Message;
import com.google.gson.Gson;

public class JsonConverter {
  private static final Gson GSON = new Gson();

  public static String convertToJsonString(Object obj) {
    return GSON.toJson(obj);
  }

  public static Message getMessageFromString(String str) {
    return GSON.fromJson(str, Message.class);
  }

}
