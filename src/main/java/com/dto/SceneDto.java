package com.dto;

import java.util.Map;

public record SceneDto(Map<String, Object> request, ExpectedResponseDto expected) {}
