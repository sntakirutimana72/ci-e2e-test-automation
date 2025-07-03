package com.page.dashboard;

import com.page.Base;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BankManagerDashboardPage extends Base {

  @FindBy(xpath = "//button[contains(., 'Add Customer')]")
  private WebElement addCustomerButton;

  @FindBy(xpath = "//button[contains(., 'Open Account')]")
  private WebElement openAccountButton;

  @FindBy(xpath = "//button[contains(., 'Customers')]")
  private WebElement customersButton;

  public BankManagerDashboardPage(WebDriver driver) {
    super(driver);
  }
  
  @Step("Click `Add Customer` option")
  public AddCustomerPage clickAddCustomer() {
    addCustomerButton.click();
    return new AddCustomerPage(driver);
  }

  @Step("Click `Open Account` option")
  public OpenAccountPage clickOpenAccount() {
    openAccountButton.click();
    return new OpenAccountPage(driver);
  }

  @Step("Click `Customers` option")
  public CustomersListPage clickCustomers() {
    customersButton.click();
    return new CustomersListPage(driver);
  }

  public boolean isLoaded() {
    try {
      waitForVisibility(addCustomerButton, 5);
      return true;
    } catch (Exception e) {
      logger.error(e.getMessage());
      return false;
    }
  }
}
