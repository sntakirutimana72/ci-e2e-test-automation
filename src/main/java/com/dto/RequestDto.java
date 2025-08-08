package com.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RequestDto {
  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private String postalCode;
}
