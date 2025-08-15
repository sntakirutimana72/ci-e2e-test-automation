package com.apis;

import com.specs.ProductRequestSpecs;
import com.specs.ResponseSpecs;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductsApi extends BaseApi {

  @Override
  public Response findAll(int expectedStatus) {
    return given()
      .spec(ProductRequestSpecs.defaultSpec())
      .when()
      .get("/")
      .then()
      .spec(ResponseSpecs.defaultSpec(expectedStatus))
      .extract()
      .response();
  }

  @Override
  public Response findById(String id, int expectedStatus) {
    return given()
      .spec(ProductRequestSpecs.defaultSpec())
      .pathParam("id", id)
      .when()
      .get("/{id}")
      .then()
      .spec(ResponseSpecs.defaultSpec(expectedStatus))
      .extract()
      .response();
  }

  @Override
  public Response findById(int id, int expectedStatus) {
    return findById(String.valueOf(id), expectedStatus);
  }

  @Override
  public Response create(Object requestBody, int expectedStatus) {
    return given()
      .spec(ProductRequestSpecs.defaultSpec())
      .body(requestBody)
      .when()
      .post("/")
      .then()
      .spec(ResponseSpecs.defaultSpec(expectedStatus))
      .extract()
      .response();
  }

  @Override
  public Response update(String id, Object requestBody, int expectedStatus) {
    return given()
      .spec(ProductRequestSpecs.defaultSpec())
      .pathParam("id", id)
      .body(requestBody)
      .when()
      .patch("/{id}")
      .then()
      .spec(ResponseSpecs.defaultSpec(expectedStatus))
      .extract()
      .response();
  }

  @Override
  public Response update(int id, Object requestBody, int expectedStatus) {
    return update(String.valueOf(id), requestBody, expectedStatus);
  }

  @Override
  public Response delete(String id, int expectedStatus) {
    return given()
      .spec(ProductRequestSpecs.defaultSpec())
      .pathParam("id", id)
      .when()
      .delete("/{id}")
      .then()
      .spec(ResponseSpecs.defaultSpec(expectedStatus))
      .extract()
      .response();
  }

  @Override
  public Response delete(int id, int expectedStatus) {
    return delete(String.valueOf(id), expectedStatus);
  }

  @Override
  public Response deleteAll(int expectedStatus) {
    return given()
      .spec(ProductRequestSpecs.defaultSpec())
      .when()
      .delete("/")
      .then()
      .spec(ResponseSpecs.defaultSpec(expectedStatus))
      .extract()
      .response();
  }
}
