package com.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

  private static final int TIMEOUT = 4;

  private Waits() {
    // Prevents instantiation of the class
  }

  private static WebDriverWait wait(WebDriver driver) {
    return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
  }

  // Wait until the element is visible
  public static void waitForElementToAppear(WebElement element, WebDriver driver) {
    wait(driver).until(ExpectedConditions.visibilityOf(element));
  }

  // Wait until the element is no longer visible (disappears)
  public static void waitForElementToDisappear(WebElement element, WebDriver driver) {
    wait(driver).until(ExpectedConditions.invisibilityOf(element));
  }
}

