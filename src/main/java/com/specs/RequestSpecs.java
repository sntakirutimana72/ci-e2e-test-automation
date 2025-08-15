package com.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestSpecs {

  public static RequestSpecification defaultSpec() {
    return new RequestSpecBuilder()
      .setContentType("application/json")
      .log(LogDetail.ALL)
      .build();
  }
}
