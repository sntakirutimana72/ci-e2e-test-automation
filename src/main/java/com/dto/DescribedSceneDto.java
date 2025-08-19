package com.dto;

import java.util.Map;

public record DescribedSceneDto(String desc, Map<String, Object> request, ExpectedResponseDto expected) {}
