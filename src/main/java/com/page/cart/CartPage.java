package com.page.cart;

import com.codeborne.selenide.ElementsCollection;
import com.page.BasePage;
import com.page.checkout.CheckoutBillingInfoPage;
import com.page.inventory.InventoryPage;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage extends BasePage {

  private ElementsCollection items() {
    return $$(".cart_item");
  }

  public List<CartItem> getItems() {
    return items().asFixedIterable()
      .stream()
      .map(CartItem::new)
      .collect(Collectors.toList());
  }

  public int count() {
    return items().size();
  }

  public void removeItem(CartItem item) {
    item.remove();
  }

  public boolean isCheckoutOptionAvailable() {
    return $("#checkout").isDisplayed();
  }

  @Step("Go back to inventory page using the `Continue Shopping` option")
  public InventoryPage continueShopping() {
    $("#continue-shopping").click();
    return new InventoryPage();
  }

  @Step("Go to checkout page")
  public CheckoutBillingInfoPage checkout() {
    $("#checkout").click();
    return new CheckoutBillingInfoPage();
  }

  @Override
  protected void prepareIsLoadedCheckpoints() {
    waitWithin(() ->
      hasElementWithText("[data-test='title']", "Your Cart") &&
      $("#continue-shopping").isDisplayed() &&
      isCheckoutOptionAvailable(),
      5
    );
  }
}
