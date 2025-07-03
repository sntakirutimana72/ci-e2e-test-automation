package com.page.dashboard;

import com.page.Base;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DepositPage extends Base {

  @FindBy(xpath = "//label[contains(., 'Amount to be Deposited :')]/following-sibling::input")
  private WebElement amountField;

  @FindBy(xpath = "//form//button[contains(., 'Deposit')]")
  private WebElement submitButton;

  @FindBy(css = ".error")
  private WebElement errorDisplay;

  public DepositPage(WebDriver driver) {
    super(driver);
  }

  @Step("Enter deposit amount")
  private void enterAmount(String amount) {
    amountField.sendKeys(amount);
  }

  @Step("Click 'Deposit' button on deposit form")
  private void submit() {
    submitButton.click();
  }

  public DepositPage deposit(String amount) {
    enterAmount(amount);
    submit();
    return this;
  }

  public boolean wasSuccessful(String message) {
    try {
      waitForTextVisibility(errorDisplay, message);
      return true;
    } catch (Exception e) {
      logger.error(e.getMessage());
      return false;
    }
  }

  public boolean isLoaded() {
    try {
      waitForVisibility(amountField, 5);
      return true;
    } catch (Exception e) {
      logger.error(e.getMessage());
      return false;
    }
  }
}
