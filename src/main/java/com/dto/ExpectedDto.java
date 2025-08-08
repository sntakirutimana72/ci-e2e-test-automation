package com.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ExpectedDto {
  private String message;
}
