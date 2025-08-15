package com.apis;

import com.specs.ResponseSpecs;
import com.specs.RequestSpecs;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApi {
  public Response signIn(Map<String, Object> credentials, int expectedStatus) {
    return given()
      .spec(RequestSpecs.defaultSpec())
      .body(credentials)
      .when()
      .post("/auth/login")
      .then()
      .spec(ResponseSpecs.defaultSpec(expectedStatus))
      .extract()
      .response();
  }

  public Response signInWithInvalidCredentials(Map<String, Object> credentials, int expectedStatus) {
    return given()
      .spec(RequestSpecs.defaultSpec())
      .body(credentials)
      .when()
      .post("/auth/login")
      .then()
      .contentType("text/html; charset=UTF-8")
      .statusCode(expectedStatus)
      .extract()
      .response();
  }
}
