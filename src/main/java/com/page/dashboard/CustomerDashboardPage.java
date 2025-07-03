package com.page.dashboard;

import com.page.Base;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerDashboardPage extends Base {

  @FindBy(xpath = "//button[contains(., 'Transactions')]")
  private WebElement transactionsButton;

  @FindBy(xpath = "//button[contains(., 'Deposit')]")
  private WebElement depositButton;

  @FindBy(xpath = "//button[contains(., 'Withdrawl')]")
  private WebElement withdrawalButton;

  public CustomerDashboardPage(WebDriver driver) {
    super(driver);
  }

  @Step("Click `Transactions' option")
  public CustomerTransactionHistoryPage clickTransactions() {
    transactionsButton.click();
    return new CustomerTransactionHistoryPage(driver);
  }

  @Step("Click `Deposit` option")
  public DepositPage clickDeposit() {
    depositButton.click();
    return new DepositPage(driver);
  }

  @Step("Click `Withdrawl` option")
  public WithdrawalPage clickWithdrawal() {
    withdrawalButton.click();
    return new WithdrawalPage(driver);
  }

  public boolean isLoaded(String username) {
    try {
      waitForVisibility(new By.ByXPath(
        String.format("//span[contains(text(), '%s')]", username)), 10);
      return true;
    } catch (Exception e) {
      logger.error(e.getMessage());
      return false;
    }
  }
}
