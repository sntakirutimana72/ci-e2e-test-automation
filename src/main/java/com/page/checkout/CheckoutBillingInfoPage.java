package com.page.checkout;

import com.page.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class CheckoutBillingInfoPage extends BasePage {

  @Override
  protected void prepareIsLoadedCheckpoints() {
    waitWithin(() ->
      $("#first-name").isDisplayed() &&
      $("#last-name").isDisplayed() &&
      $("#postal-code").isDisplayed() &&
      $("#continue").isDisplayed(),
      5
    );
  }

  public boolean hasErrorMessage(String message) {
    return waitForElementWithText("h3[data-test='error']", message);
  }

  @Step("Enter first name: {name}")
  void enterFirstName(String name) {
    $("#first-name").setValue(name);
  }

  @Step("Enter last name: {name}")
  void enterLastName(String name) {
    $("#last-name").setValue(name);
  }

  @Step("Enter postal code: {code}")
  void enterPostalCode(String code) {
    $("#postal-code").setValue(code);
  }

  @Step("Click Continue button")
  void clickContinue() {
    $("#continue").click();
  }

  public CheckoutOverviewPage fillInfo(String firstName, String lastName, String postalCode) {
    enterFirstName(firstName);
    enterLastName(lastName);
    enterPostalCode(postalCode);
    clickContinue();
    return new CheckoutOverviewPage();
  }
}
