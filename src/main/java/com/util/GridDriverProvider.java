package com.util;

import com.codeborne.selenide.WebDriverProvider;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class GridDriverProvider implements WebDriverProvider {

  @Nonnull
  @Override
  public WebDriver createDriver(@Nonnull Capabilities ignored) {
    String browser = System.getProperty("CURRENT_BROWSER");
    String version = System.getProperty("CURRENT_BROWSER_VERSION");
    String hubUrl = System.getProperty("SELENIUM_HUB_URI", Envs.SELENIUM_HUB_URI);

    log.info("Creating remote WebDriver for {} at {}", browser, hubUrl);

    try {
      Capabilities capabilities = CapabilitiesFactory.create(browser, version);
      capabilities.asMap().forEach((key, value) -> log.info("{}: {}", key, value));

      log.info("Browser capabilities: {}", capabilities);

      RemoteWebDriver driver = new RemoteWebDriver(new URL(hubUrl), capabilities);

      log.info("Successfully created WebDriver session: {}", driver.getSessionId());

      return driver;
    } catch (MalformedURLException e) {
      log.error("Invalid hub URL: {}", hubUrl, e);
      throw new RuntimeException("Invalid hub URL: " + hubUrl, e);
    } catch (Exception e) {
      log.error("Failed to create WebDriver for browser '{}' at hub '{}': {}", browser, hubUrl, e.getMessage());
      throw new RuntimeException("Failed to create WebDriver", e);
    }
  }
}