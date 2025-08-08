package com.page;

import com.page.inventory.InventoryPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

  @Step("Enter username: {username}")
  public void enterUsername(String username) {
    $("#user-name").sendKeys(username);
  }

  @Step("Enter username: {username}")
  public void enterPassword(String password) {
    $("#password").sendKeys(password);
  }

  public boolean hasErrorMessage(String message) {
    return waitForElementWithText("h3[data-test='error']", message);
  }

  @Step("Click `Login` button")
  private void clickLogin() {
    $("#login-button").click();
  }

  public InventoryPage login(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    clickLogin();

    return new InventoryPage();
  }

  @Override
  protected void prepareIsLoadedCheckpoints() {
    waitWithin(() ->
      $("#login-button").isEnabled() &&
      $("#user-name").isDisplayed() &&
      $("#password").isDisplayed(),
      5
    );
  }
}