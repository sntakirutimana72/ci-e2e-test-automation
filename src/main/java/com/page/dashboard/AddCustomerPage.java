package com.page.dashboard;

import com.page.Base;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCustomerPage extends Base {

  @FindBy(xpath = "//label[contains(., 'First Name :')]/following-sibling::input")
  private WebElement firstnameField;

  @FindBy(xpath = "//label[contains(., 'Last Name :')]/following-sibling::input")
  private WebElement lastnameField;

  @FindBy(xpath = "//label[contains(., 'Post Code :')]/following-sibling::input")
  private WebElement postalCodeField;

  @FindBy(xpath = "//form//button[contains(., 'Add Customer')]")
  private WebElement submitButton;

  public AddCustomerPage(WebDriver driver) {
    super(driver);
  }

  @Step("Enter firstname")
  public void enterFirstname(String name) {
    firstnameField.sendKeys(name);
  }

  @Step("Enter lastname")
  public void enterLastname(String name) {
    lastnameField.sendKeys(name);
  }

  @Step("Enter postal code")
  public void enterPostalCode(String postalCode) {
    postalCodeField.sendKeys(postalCode);
  }

  @Step("Click `Add Customer` button on the new customer form")
  public void submit() {
    submitButton.click();
  }

  public AddCustomerPage add(String firstname, String lastname, String postalCode) {
    enterFirstname(firstname);
    enterLastname(lastname);
    enterPostalCode(postalCode);
    submit();
    return this;
  }

  public boolean isLoaded() {
    try {
      waitForVisibility(firstnameField, 5);
      return true;
    } catch (Exception e) {
      logger.error(e.getMessage());
      return false;
    }
  }
}
