package com.dto.login;

import com.dto.ScenarioDto;

import java.util.List;

public record LoginDto(List<ScenarioDto> failure, List<ScenarioDto> success) {}
