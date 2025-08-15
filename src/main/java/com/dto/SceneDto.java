package com.dto;

import lombok.Data;

@Data
public class SceneDto {
  private RequestDto request;
  private ExpectedResponseDto expected;
}
