package com.page.checkout;

import com.page.BasePage;
import com.page.inventory.InventoryPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutCompletePage extends BasePage {

  @Step("Back to products")
  public InventoryPage backToProducts() {
    $("#back-to-products").click();
    return new InventoryPage();
  }

  @Override
  protected void prepareIsLoadedCheckpoints() {
    waitWithin(() ->
      hasElementWithText("[data-test='complete-header']", "Thank you for your order!") &&
      $("#back-to-products").isDisplayed(),
      5
    );
  }
}
