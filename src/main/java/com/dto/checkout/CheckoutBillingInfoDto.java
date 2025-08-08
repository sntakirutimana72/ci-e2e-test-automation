package com.dto.checkout;

import com.dto.ScenarioDto;

import java.util.List;

public record CheckoutBillingInfoDto(List<ScenarioDto> valid, List<ScenarioDto> invalid) {}
