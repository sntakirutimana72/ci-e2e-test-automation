package com.page;

import com.util.WaitUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class Base {

  protected final WebDriver driver;

  public Base(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  /**
   * Moves the mouse cursor over the specified {@link WebElement}, triggering any hover events
   * such as tooltips or dropdown menus.
   * <p>
   * This method is useful for testing UI behaviors that rely on hover interactions.
   * </p>
   *
   * @param element the {@link WebElement} to hover over
   */
  public void hover(WebElement element) {
    Actions actions = new Actions(driver);
    actions.moveToElement(element).perform();
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
   * Waits until the specified {@link WebElement} is no longer visible on the page
   * within the given timeout period.
   * <p>
   * This method is a wrapper around {@link WaitUtil#waitForDisappearance(WebElement, int, WebDriver)},
   * using the current {@link WebDriver} instance.
   * </p>
   *
   * @param element       the {@link WebElement} to wait for the disappearance
   * @param timeoutInSecs the maximum time to wait in seconds for the element to become invisible
   * @throws org.openqa.selenium.TimeoutException if the element does not disappear within the timeout
   * @throws org.openqa.selenium.StaleElementReferenceException if the element is no longer attached to the DOM
   */
  public void waitForDisappearance(WebElement element, int timeoutInSecs) {
    WaitUtil.waitForDisappearance(element, timeoutInSecs, driver);
  }

  /**
   * Waits until the element located by the specified {@link By} locator is no longer visible
   * on the page within the given timeout period.
   * <p>
   * This method is a wrapper around {@link WaitUtil#waitForDisappearance(By, int, WebDriver)},
   * using the class's current {@link WebDriver} instance.
   * </p>
   *
   * @param locator       the {@link By} locator used to find the element to wait for the disappearance
   * @param timeoutInSecs the maximum time to wait in seconds for the element to become invisible
   * @throws org.openqa.selenium.TimeoutException if the element does not disappear within the timeout
   */
  public void waitForDisappearance(By locator, int timeoutInSecs) {
    WaitUtil.waitForDisappearance(locator, timeoutInSecs, driver);
  }
}
