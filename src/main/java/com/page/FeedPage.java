package com.page;

import com.page.modal.Subscribed;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FeedPage extends Base {

  @FindBy(how = How.ID, using = "email")
  private WebElement email;

  @FindBy(how = How.ID, using = "error")
  private WebElement errorDisplay;

  @FindBy(how = How.CLASS_NAME, using = "submit-btn")
  private WebElement subscribeButton;

  public FeedPage(WebDriver driver) {
    super(driver);
  }

  public void enterEmail(String value) {
    email.sendKeys(value);
  }

  public void hitSubscribe() {
    subscribeButton.click();
  }

  public String error() {
    return errorDisplay.getText();
  }

  public Subscribed subscribe(String value) {
    enterEmail(value);
    hitSubscribe();

    return new Subscribed(driver);
  }

  public Subscribed subscribeUsingEnter(String value) {
    enterEmail(value);
    email.sendKeys(Keys.ENTER);

    return new Subscribed(driver);
  }

  public Subscribed subscribeUsingSpace(String value) {
    enterEmail(value);
    moveTo(subscribeButton);
    subscribeButton.sendKeys(Keys.SPACE);

    return new Subscribed(driver);
  }
}
