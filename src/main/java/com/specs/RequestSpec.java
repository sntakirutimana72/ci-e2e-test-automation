package com.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestSpec {

  public static RequestSpecification requestSpec() {
    return new RequestSpecBuilder()
      .setBaseUri("https://jsonplaceholder.typicode.com")
      .setContentType("application/json")
      .log(LogDetail.ALL)
      .build();
  }
}
