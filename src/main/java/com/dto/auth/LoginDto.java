package com.dto.auth;

import com.dto.DescribedSceneDto;

import java.util.List;

public record LoginDto(List<DescribedSceneDto> valid, List<DescribedSceneDto> invalid) {}
