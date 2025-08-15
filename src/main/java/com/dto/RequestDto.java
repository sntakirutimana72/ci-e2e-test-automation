package com.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class RequestDto {
  // Login credentials payload
  private String username;
  private String password;

  public Map<String, Object> asLogin() {
    Map<String, Object> payload = new HashMap<>();
    payload.put("username", getUsername());
    payload.put("password", getPassword());

    return refineMapPayload(payload);
  }

  private Map<String, Object> refineMapPayload(Map<String, Object> rawPayload) {
    Map<String, Object> refined = new HashMap<>(rawPayload);
    refined.values().removeIf(Objects::isNull);
    return refined;
  }
}
