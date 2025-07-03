package com.page;

import com.util.SystemLogger;
import com.util.WaitUtil;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

public abstract class Base {

  protected final WebDriver driver;
  protected Logger logger;

  public Base(WebDriver driver) {
    this.driver = driver;
    this.logger = SystemLogger.getLogger(getClass());

    PageFactory.initElements(driver, this);
  }

  /**
   * Selects an option within the specified {@link WebElement} <code>&lt;select&gt;</code> element
   * by matching its <code>value</code> attribute.
   * <p>
   * This method wraps Selenium's {@link Select} functionality, allowing you to easily
   * select dropdown options by their <code>value</code> attribute.
   * </p>
   *
   * @param value         the <code>value</code> attribute of the option to be selected
   * @param selectElement the {@link WebElement} representing the <code>&lt;select&gt;</code> element
   * @throws org.openqa.selenium.NoSuchElementException if no option with the specified value is found
   */
  public static void selectByText(String value, WebElement selectElement) {
    Select select = new Select(selectElement);
    select.selectByVisibleText(value);
  }

  /**
   * Waits until the element located by the specified {@link By} locator becomes visible on the page
   * within the given timeout period and then returns the found {@link WebElement}.
   * <p>
   * This method is a convenient wrapper around the {@link WaitUtil#waitForVisibility(By, int, WebDriver)}
   * utility, using the class's existing {@link WebDriver} instance.
   * </p>
   *
   * @param locator       the {@link By} locator used to find the element to wait for
   * @param timeoutInSecs the maximum time to wait in seconds for the element to become visible
   * @return the {@link WebElement} once it becomes visible
   * @throws org.openqa.selenium.TimeoutException if the element does not become visible within the timeout
   * @throws org.openqa.selenium.NoSuchElementException if no matching element is found in the DOM
   */
  public WebElement waitForVisibility(By locator, int timeoutInSecs) {
    return WaitUtil.waitForVisibility(locator, timeoutInSecs, driver);
  }

  /**
   * Waits until the specified {@link WebElement} becomes visible on the page within the given timeout period.
   * <p>
   * This method is a convenient wrapper around {@link WaitUtil#waitForVisibility(WebElement, int, WebDriver)},
   * using the class's existing {@link WebDriver} instance.
   * </p>
   *
   * @param element       the {@link WebElement} to wait for visibility
   * @param timeoutInSecs the maximum time to wait in seconds for the element to become visible
   * @throws org.openqa.selenium.TimeoutException if the element does not become visible within the timeout
   * @throws org.openqa.selenium.StaleElementReferenceException if the element is no longer attached to the DOM
   */
  public void waitForVisibility(WebElement element, int timeoutInSecs) {
    WaitUtil.waitForVisibility(element, timeoutInSecs, driver);
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
   * @throws org.openqa.selenium.TimeoutException if the expected text does not appear within the timeout
   */
  public void waitForTextVisibility(WebElement element, String expected) {
    WaitUtil.waitForTextVisibility(element, expected, 5, driver);
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
   * @param timeoutInSecs the maximum time to wait in seconds for the alert to appear
   * @return the {@link Alert} object once it becomes present
   * @throws org.openqa.selenium.TimeoutException if the alert does not appear within the timeout
   */
  public Alert waitForAlertPresence(int timeoutInSecs) {
    return WaitUtil.waitForAlertPresence(driver, timeoutInSecs);
  }
}
