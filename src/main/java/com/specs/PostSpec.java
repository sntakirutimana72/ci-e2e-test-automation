package com.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSpec {

  public static RequestSpecification requestSpec() {
    return new RequestSpecBuilder()
      .addRequestSpecification(RequestSpec.requestSpec())
      .setBasePath("/posts")
      .build();
  }
}
