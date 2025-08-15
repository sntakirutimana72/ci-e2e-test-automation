package com.apis;

import io.restassured.response.Response;

public abstract class BaseApi {

  public abstract Response findAll(int expectedStatus);
  public abstract Response findById(String id, int expectedStatus);
  public abstract Response findById(int id, int expectedStatus);

  public abstract Response create(Object requestBody, int expectedStatus);
  public abstract Response update(String id, Object requestBody, int expectedStatus);
  public abstract Response update(int id, Object requestBody, int expectedStatus);

  public abstract Response delete(String id, int expectedStatus);
  public abstract Response delete(int id, int expectedStatus);
  public abstract Response deleteAll(int expectedStatus);
}
