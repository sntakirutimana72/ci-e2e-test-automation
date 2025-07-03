package com.util;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

  private WaitUtil() {
    // Prevents instantiation of the class
  }

  private static WebDriverWait wait(int timeoutInSecs, WebDriver driver) {
    return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSecs));
  }

  /**
   * Waits for the visibility of an element located by the specified {@link By} locator within the given timeout period.
   * <p>
   * This method uses an explicit wait to pause execution until the element becomes visible on the page,
   * up to the specified timeout in seconds. If the element does not become visible within the timeout,
   * a {@link org.openqa.selenium.TimeoutException} will be thrown.
   * </p>
   *
   * @param locator       the {@link By} locator used to find the element
   * @param timeoutInSecs the maximum time to wait in seconds for the element to become visible
   * @param driver        the {@link WebDriver} instance used to perform the wait
   * @return the {@link WebElement} once it becomes visible
   * @throws org.openqa.selenium.TimeoutException if the element does not become visible within the timeout
   * @throws org.openqa.selenium.NoSuchElementException if the locator does not find any element in the DOM
   */
  public static WebElement waitForVisibility(By locator, int timeoutInSecs, WebDriver driver) {
    return wait(timeoutInSecs, driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  /**
   * Waits for the visibility of an element specified {@link WebElement} proxy within the given timeout period.
   * <p>
   * This method uses an explicit wait to pause execution until the element becomes visible on the page,
   * up to the specified timeout in seconds. If the element does not become visible within the timeout,
   * a {@link org.openqa.selenium.TimeoutException} will be thrown.
   * </p>
   *
   * @param element       the {@link WebElement} element proxy used to find the element
   * @param timeoutInSecs the maximum time to wait in seconds for the element to become visible
   * @param driver        the {@link WebDriver} instance used to perform the wait
   * @throws org.openqa.selenium.TimeoutException if the element does not become visible within the timeout
   * @throws org.openqa.selenium.NoSuchElementException if the locator does not find any element in the DOM
   */
  public static void waitForVisibility(WebElement element, int timeoutInSecs, WebDriver driver) {
    wait(timeoutInSecs, driver).until(ExpectedConditions.visibilityOf(element));
  }

  /**
   * Waits until the specified {@link WebElement} contains the expected text
   * within the given timeout period.
   * <p>
   * This is commonly used to wait for a dropdown selection or a dynamically updated
   * field to display the expected text.
   * </p>
   *
   * @param element       the {@link WebElement} to check for the expected text
   * @param expected      the expected text to be present in the element
   * @param timeoutInSecs the maximum time to wait in seconds for the expected text to appear
   * @param driver        the {@link WebDriver} instance used to perform the wait
   * @throws org.openqa.selenium.TimeoutException if the expected text does not appear within the timeout
   */
  public static void waitForTextVisibility(WebElement element, String expected, int timeoutInSecs, WebDriver driver) {
    wait(timeoutInSecs, driver)
      .until(ExpectedConditions.textToBePresentInElement(element, expected));
  }

  /**
   * Waits explicitly for a JavaScript alert to be present on the page within the specified timeout.
   * <p>
   * This method uses Selenium's WebDriverWait to poll for the presence of an alert dialog
   * (such as alerts triggered by JavaScript's <code>alert()</code>, <code>confirm()</code>, or <code>prompt()</code>).
   * Once detected, it returns the Selenium {@link Alert} object, allowing further actions like
   * {@link Alert#getText()}, {@link Alert#accept()}, or {@link Alert#dismiss()}.
   * </p>
   *
   * @param driver the instance of {@link WebDriver} controlling the browser
   * @param timeoutInSecs the maximum time to wait in seconds for the alert to appear
   * @return the {@link Alert} object once it becomes present
   * @throws org.openqa.selenium.TimeoutException if the alert does not appear within the timeout
   */
  public static Alert waitForAlertPresence(WebDriver driver, int timeoutInSecs) {
    return wait(timeoutInSecs, driver).until(ExpectedConditions.alertIsPresent());
  }
}

