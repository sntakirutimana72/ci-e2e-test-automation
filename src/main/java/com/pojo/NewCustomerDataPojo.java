package com.pojo;

public record NewCustomerDataPojo(
  String[][] valid,
  String[][] invalidNames,
  String[][] invalidPostalCode
) {}
