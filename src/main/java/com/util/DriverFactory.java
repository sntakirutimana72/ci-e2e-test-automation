package com.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;

public class DriverFactory {

  private static WebDriver driver;

  private DriverFactory() {
    // Prevent instantiation of this class
  }

  public static WebDriver chromedriver() {
    // Default chrome options
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless=new");
    // Setup chrome driver automatically
    WebDriverManager.chromedriver().setup();
    // Initiate an instance of the chrome driver
    driver = new ChromeDriver(options);

    return driver;
  }

  public static void terminate() {
    if (Objects.nonNull(driver))
      driver.quit();
  }
}
