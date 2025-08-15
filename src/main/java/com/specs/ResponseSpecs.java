package com.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseSpecs {

  public static ResponseSpecification defaultSpec(int statusCode) {
    return new ResponseSpecBuilder()
      .expectStatusCode(statusCode)
      .expectContentType("application/json")
      .build();
  }
}
