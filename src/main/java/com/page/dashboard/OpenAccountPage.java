package com.page.dashboard;

import com.page.Base;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenAccountPage extends Base {

  @FindBy(id = "userSelect")
  private WebElement customerSelector;

  @FindBy(id = "currency")
  private WebElement currencySelector;

  @FindBy(xpath = "//form//button[contains(., 'Process')]")
  private WebElement processButton;

  public OpenAccountPage(WebDriver driver) {
    super(driver);
  }

  @Step("Select customer: {name}")
  public void selectCustomer(String name) {
    selectByText(name, customerSelector);
    waitForTextVisibility(customerSelector, name);
  }

  @Step("Select currency: {currency}")
  public void selectCurrency(String currency) {
    selectByText(currency, currencySelector);
    waitForTextVisibility(currencySelector, currency);
  }

  @Step("Click the `Process` button")
  public void submit() {
    processButton.click();
  }

  public void process(String customerName, String currency) {
    selectCustomer(customerName);
    selectCurrency(currency);
    submit();
  }

  public boolean isLoaded() {
    try {
      waitForVisibility(customerSelector, 5);
      return true;
    } catch (Exception e) {
      logger.error(e.getMessage());
      return false;
    }
  }
}
