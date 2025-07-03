package com.pojo;

public record RootDataPojo(
  LoginDataPojo login,
  TransactDataPojo transact,
  NewCustomerDataPojo newCustomer,
  AccountDataPojo account
) {}
