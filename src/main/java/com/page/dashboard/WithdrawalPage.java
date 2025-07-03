package com.page.dashboard;

import com.page.Base;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WithdrawalPage extends Base {

  @FindBy(xpath = "//label[contains(., 'Amount to be Withdrawn :')]/following-sibling::input")
  private WebElement amountField;

  @FindBy(xpath = "//form//button[contains(., 'Withdraw')]")
  private WebElement submitButton;

  @FindBy(css = ".error")
  private WebElement errorDisplay;

  public WithdrawalPage(WebDriver driver) {
    super(driver);
  }

  @Step("Enter amount you wish to withdraw")
  private void enterAmount(String amount) {
    amountField.sendKeys(amount);
  }
  
  @Step("Click `Withdraw` button on the withdrawal form")
  private void submit() {
    submitButton.click();
  }

  public WithdrawalPage withdraw(String amount) {
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
