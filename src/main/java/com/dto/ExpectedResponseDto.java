package com.dto;

import lombok.Data;

@Data
public class ExpectedResponseDto {
  private int status;
  private String message;
  private String schema;
}
