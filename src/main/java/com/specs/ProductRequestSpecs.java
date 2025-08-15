package com.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductRequestSpecs {

  public static RequestSpecification defaultSpec() {
    return new RequestSpecBuilder()
      .addRequestSpecification(RequestSpecs.defaultSpec())
      .setBasePath("/products")
      .build();
  }
}
