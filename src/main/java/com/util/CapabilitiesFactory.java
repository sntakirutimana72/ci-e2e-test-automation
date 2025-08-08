package com.util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.Map;

@Slf4j
public class CapabilitiesFactory {

  public static MutableCapabilities create(String browser, String version) {
    return switch (browser) {
      case "chrome" -> getChromeOptions(version);
      case "firefox" -> getFirefoxOptions(version);
      default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
    };
  }

  private static ChromeOptions getChromeOptions(String version) {
    ChromeOptions options = new ChromeOptions();

    // Essential Chrome arguments for container environment
    options.addArguments("--headless=new");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-gpu");
    options.addArguments("--disable-extensions");
    options.addArguments("--disable-web-security");
    options.addArguments("--disable-features=VizDisplayCompositor");
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--disable-background-timer-throttling");
    options.addArguments("--disable-backgrounding-occluded-windows");
    options.addArguments("--disable-renderer-backgrounding");

    // Chrome version to be selected
    options.setCapability("browserVersion", version);

    // Selenoid specific capabilities
    options.setCapability("selenoid:options", Map.of(
      "enableVNC", false,
      "enableVideo", false,
      "enableLog", true
    ));

    return options;
  }

  private static FirefoxOptions getFirefoxOptions(String version) {
    FirefoxOptions options = new FirefoxOptions();
    FirefoxProfile profile = new FirefoxProfile();

    // Firefox specific arguments
    options.addArguments("--headless");
    options.addArguments("--width=1920");
    options.addArguments("--height=1080");

    // Profile preferences
    profile.setPreference("browser.privatebrowsing.autostart", true);
    profile.setPreference("security.sandbox.content.level", 5);
    profile.setPreference("media.navigator.permission.disabled", true);

    options.setProfile(profile);

    // Chrome version to be selected
    options.setCapability("browserVersion", version);

    // Selenoid specific capabilities
    options.setCapability("selenoid:options", Map.of(
      "enableVNC", false,
      "enableVideo", false,
      "enableLog", true
    ));

    return options;
  }
}