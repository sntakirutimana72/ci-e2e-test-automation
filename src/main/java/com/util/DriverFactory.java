package com.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

  private DriverFactory() {
    // Prevent instantiation of this class
  }

  public static WebDriver chromedriver() {
    // Default browser options
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless=new");
    // Setup browser driver automatically
    WebDriverManager.chromedriver().setup();
    // Initiate an instance of the browser driver
    return new ChromeDriver(options);
  }

  public static WebDriver firefoxDriver() {
    // Default browser options
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--headless");
    // Setup browser driver automatically
    WebDriverManager.firefoxdriver().setup();
    // Initiate an instance of the browser driver
    return new FirefoxDriver(options);
  }

  public static WebDriver edgeDriver() {
    // Default browser options
    EdgeOptions options = new EdgeOptions();
    options.addArguments("--headless");
    // Setup browser driver automatically
    WebDriverManager.edgedriver().setup();
    // Initiate an instance of the browser driver
    return new EdgeDriver(options);
  }
}
