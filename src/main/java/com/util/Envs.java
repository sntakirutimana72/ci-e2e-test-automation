package com.util;

import io.github.cdimascio.dotenv.Dotenv;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Envs {

  private static final Dotenv envs = Dotenv
    .configure()
    .directory(".")
    .filename(".env.test")
    .ignoreIfMissing()
    .load();

  // Base urls
  public static String AUT_BASE_URI = envs.get("AUT_BASE_URI");

  // Test data source
  public static String TEST_DATA_SOURCE = envs.get("TEST_DATA_SOURCE");

  public static String get(String key) {
    return envs.get(key);
  }
}
