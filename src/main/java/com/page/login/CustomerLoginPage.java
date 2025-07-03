package com.page.login;

import com.page.Base;
import com.page.dashboard.CustomerDashboardPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerLoginPage extends Base {

  @FindBy(id = "userSelect")
  private WebElement customerSelector;

  @FindBy(css = ".btn.btn-default")
  private WebElement loginButton;

  public CustomerLoginPage(WebDriver driver) {
    super(driver);
  }

  @Step("Select customer: {name}")
  private void selectCustomer(String name) {
    waitForVisibility(customerSelector, 5);
    selectByText(name, customerSelector);
    waitForTextVisibility(customerSelector, name);
    waitForVisibility(loginButton, 1);
  }

  @Step("Click `Login` button")
  private void clickLogin() {
    loginButton.click();
  }

  public CustomerDashboardPage login(String customerName) {
    selectCustomer(customerName);
    clickLogin();

    return new CustomerDashboardPage(driver);
  }
}
