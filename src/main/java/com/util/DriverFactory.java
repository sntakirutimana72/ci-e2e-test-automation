package com.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;

public class DriverFactory {

  private static final Logger logger = SystemLogger.getLogger(DriverFactory.class);

  private DriverFactory() {
    // Prevent instantiation of this class
  }

  public static WebDriver chromedriver() {
    // Default browser options
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless=new");
    options.addArguments(
      "--user-data-dir=/tmp/chrome-profile-" +
        Thread.currentThread().getId() + "-" +
        System.currentTimeMillis()
    );

    // Additional isolation options
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-extensions");
    options.addArguments("--disable-gpu");
    options.addArguments("--disable-web-security");
    options.addArguments("--disable-features=VizDisplayCompositor");
    options.addArguments("--remote-allow-origins=*");

    // Use different ports to avoid conflicts
    options.addArguments("--port=" + getAvailablePort());

    // Setup browser driver automatically
    WebDriverManager.chromedriver().setup();

    // Initiate an instance of the browser driver
    return new ChromeDriver(options);
  }

  public static WebDriver firefoxDriver() {
    // Default browser options
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--headless");

    // Create a unique profile for each thread
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("browser.privatebrowsing.autostart", true);
    options.setProfile(profile);

    // Setup browser driver automatically
    WebDriverManager.firefoxdriver().setup();

    // Initiate an instance of the browser driver
    return new FirefoxDriver(options);
  }

  public static WebDriver edgeDriver() {
    // Default browser options
    EdgeOptions options = new EdgeOptions();
    options.addArguments("--headless=new");
    options.addArguments(
      "--user-data-dir=/tmp/edge-profile-" +
        Thread.currentThread().getId() + "-" +
        System.currentTimeMillis()
    );

    // Add isolation options
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-extensions");
    options.addArguments("--disable-gpu");
    options.addArguments("--remote-allow-origins=*");

    // Use different ports to avoid conflicts
    options.addArguments("--port=" + getAvailablePort());

    // Setup browser driver automatically
    WebDriverManager.edgedriver().setup();

    // Initiate an instance of the browser driver
    try {
      return new EdgeDriver(options);
    } catch (Exception e) {
      logger.error("Failed to create Edge driver: {}", e.getMessage());
      throw new RuntimeException("Edge driver initialization failed", e);
    }
  }

  private static int getAvailablePort() {
    try (ServerSocket socket = new ServerSocket(0)) {
      return socket.getLocalPort();
    } catch (IOException e) {
      return 9515 + (int)(Math.random() * 1000);
    }
  }
}
