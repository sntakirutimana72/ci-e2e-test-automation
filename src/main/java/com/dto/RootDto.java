package com.dto;

import com.dto.auth.LoginDto;
import com.dto.products.ProductsDto;

public record RootDto(LoginDto login, ProductsDto products) {}
