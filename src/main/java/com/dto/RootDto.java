package com.dto;

import com.dto.checkout.CheckoutDto;
import com.dto.login.LoginDto;

public record RootDto(LoginDto login, CheckoutDto checkout) {}
