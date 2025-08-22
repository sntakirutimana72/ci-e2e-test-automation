package com.page;

import com.util.Waits;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public abstract class Base {

  protected final WebDriver driver;

  @FindBy(how = How.TAG_NAME, using = "body")
  private WebElement body;

  public Base(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void moveTo(WebElement element) {
    Actions actions = new Actions(driver);
    actions.moveToElement(element).perform();
  }

  public void pressEscape() {
    body.sendKeys(Keys.ESCAPE);
  }

  public void waitForElementToAppear(WebElement element) {
    Waits.waitForElementToAppear(element, driver);
  }

  public void waitForElementToDisappear(WebElement element) {
    Waits.waitForElementToDisappear(element, driver);
  }
}
