package com.util;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Envs {
  // Load environment variables file
  private static final Dotenv $ = Dotenv.configure()
    .directory(".")
    .filename(".env.test")
    .ignoreIfMissing()
    .load();

  // Selenium Hub URI
  public static final String SELENIUM_HUB_URI = $.get("SELENIUM_HUB_URI");

  // AUT BasePage URI
  public static final String AUT_BASE_URI = $.get("AUT_BASE_URI");

  public static String get(String key) {
    return $.get(key);
  }
}
