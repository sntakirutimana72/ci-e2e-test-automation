package com.api;

import com.specs.PostSpec;
import com.specs.ResponseSpec;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostAPI {

  public static Response getPosts(int status) {
    return given()
      .spec(PostSpec.requestSpec())
      .when()
      .get("/")
      .then()
      .spec(ResponseSpec.defaultSpec(status))
      .extract()
      .response();
  }

  public static Response getCommentsForPost(int id, int status) {
    return given()
      .spec(PostSpec.requestSpec())
      .pathParam("id", id)
      .when()
      .get("/{id}/comments")
      .then()
      .spec(ResponseSpec.defaultSpec(status))
      .extract()
      .response();
  }

  public static Response getPostById(int id, int status) {
    return given()
      .spec(PostSpec.requestSpec())
      .pathParam("id", id)
      .when()
      .get("/{id}")
      .then()
      .spec(ResponseSpec.defaultSpec(status))
      .extract()
      .response();
  }

  public static Response createPost(Object payload, int status) {
    return given()
      .spec(PostSpec.requestSpec())
      .body(payload)
      .when()
      .post("/")
      .then()
      .spec(ResponseSpec.defaultSpec(status))
      .extract()
      .response();
  }

  public static Response updatePost(int id, Object payload, int status) {
    return given()
      .spec(PostSpec.requestSpec())
      .pathParam("id", id)
      .body(payload)
      .when()
      .put("/{id}")
      .then()
      .spec(ResponseSpec.defaultSpec(status))
      .extract()
      .response();
  }

  public static Response deletePost(int id, int status) {
    return given()
      .spec(PostSpec.requestSpec())
      .pathParam("id", id)
      .when()
      .delete("/{id}")
      .then()
      .spec(ResponseSpec.defaultSpec(status))
      .extract()
      .response();
  }
}
