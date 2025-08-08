package com.page.checkout;

import com.page.BasePage;
import com.page.cart.CartItem;
import io.qameta.allure.Step;

import static com.util.FormatUtil.extractPrice;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutOverviewPage extends BasePage {

  public double getComputeSubtotal() {
    return $$(".cart_item").asFixedIterable()
      .stream()
      .map(CartItem::new)
      .mapToDouble(it -> extractPrice(it.getPrice()))
      .sum();
  }

  public double getSubtotal() {
    return extractPrice($("[data-test='subtotal-label']").getText());
  }

  public double getComputedTotal() {
    return getComputeSubtotal() +
      extractPrice($("[data-test='tax-label']").getText());
  }

  public double getTotal() {
    return extractPrice($("[data-test='total-label']").getText());
  }

  @Step("Finish checkout")
  public CheckoutCompletePage finishCheckout() {
    $("#finish").click();
    return new CheckoutCompletePage();
  }

  @Override
  protected void prepareIsLoadedCheckpoints() {
    waitWithin(() ->
        !$$(".cart_item").isEmpty() &&
          $("#finish").isDisplayed() &&
          $("[data-test='subtotal-label']").isDisplayed() &&
          $("[data-test='tax-label']").isDisplayed() &&
          $("[data-test='total-label']").isDisplayed(),
      10
    );
  }
}
