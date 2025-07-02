package com.util;

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
   * Waits until the element located by the specified {@link By} locator is clickable within the given timeout period,
   * and then returns the found {@link WebElement}.
   * <p>
   * This method uses an explicit wait to pause execution until the element is both visible and enabled,
   * meaning it is ready to be clicked. If the element does not become clickable within the specified
   * timeout period, a {@link org.openqa.selenium.TimeoutException} will be thrown.
   * </p>
   *
   * @param locator       the {@link By} locator used to find the element to wait for
   * @param timeoutInSecs the maximum time to wait in seconds for the element to become clickable
   * @param driver        the {@link WebDriver} instance used to perform the wait
   * @return the {@link WebElement} once it becomes clickable
   * @throws org.openqa.selenium.TimeoutException if the element does not become clickable within the timeout
   * @throws org.openqa.selenium.NoSuchElementException if no element is found with the given locator
   */
  public static WebElement waitForClickable(By locator, int timeoutInSecs, WebDriver driver) {
    return wait(timeoutInSecs, driver).until(ExpectedConditions.elementToBeClickable(locator));
  }

  /**
   * Waits until the specified {@link WebElement} is clickable within the given timeout period.
   * <p>
   * This method uses an explicit wait to pause execution until the element is both visible and enabled
   * such that a click operation can be performed. If the element does not become clickable within the
   * specified timeout, a {@link org.openqa.selenium.TimeoutException} will be thrown.
   * </p>
   *
   * @param element       the {@link WebElement} to wait for to become clickable
   * @param timeoutInSecs the maximum time to wait in seconds for the element to be clickable
   * @param driver        the {@link WebDriver} instance used to perform the wait
   * @throws org.openqa.selenium.TimeoutException if the element does not become clickable within the timeout
   * @throws org.openqa.selenium.StaleElementReferenceException if the element is no longer attached to the DOM
   */
  public static void waitForClickable(WebElement element, int timeoutInSecs, WebDriver driver) {
    wait(timeoutInSecs, driver).until(ExpectedConditions.elementToBeClickable(element));
  }

  /**
   * Waits until the specified {@link WebElement} is no longer visible on the page within the given timeout period.
   * <p>
   * This method is useful for waiting for overlays, loaders, or any transient element to disappear
   * before proceeding with further actions. If the element does not become invisible within the specified
   * timeout period, a {@link org.openqa.selenium.TimeoutException} will be thrown.
   * </p>
   *
   * @param element       the {@link WebElement} to wait for to become invisible
   * @param timeoutInSecs the maximum time to wait in seconds for the element to disappear
   * @param driver        the {@link WebDriver} instance used to perform the wait
   * @throws org.openqa.selenium.TimeoutException if the element does not disappear within the timeout
   * @throws org.openqa.selenium.StaleElementReferenceException if the element is no longer attached to the DOM
   */
  public static void waitForDisappearance(WebElement element, int timeoutInSecs, WebDriver driver) {
    wait(timeoutInSecs, driver).until(ExpectedConditions.invisibilityOf(element));
  }

  /**
   * Waits until the element located by the specified {@link By} locator is no longer visible on the page
   * within the given timeout period.
   * <p>
   * This method is particularly useful for waiting for dynamic elements, such as pop-ups or loading
   * indicators identified by locators, to disappear before continuing the test flow.
   * If the element does not become invisible within the timeout period, a {@link org.openqa.selenium.TimeoutException} will be thrown.
   * </p>
   *
   * @param locator       the {@link By} locator used to find the element to wait for the disappearance
   * @param timeoutInSecs the maximum time to wait in seconds for the element to disappear
   * @param driver        the {@link WebDriver} instance used to perform the wait
   * @throws org.openqa.selenium.TimeoutException if the element does not disappear within the timeout
   */
  public static void waitForDisappearance(By locator, int timeoutInSecs, WebDriver driver) {
    wait(timeoutInSecs, driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
  }
}

