package com.page.inventory;

import com.codeborne.selenide.ElementsCollection;
import com.page.BasePage;
import com.page.cart.CartPage;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InventoryPage extends BasePage {

  public ElementsCollection getProductElements() {
    return $$(".inventory_item");
  }

  public List<InventoryItem> getItems() {
    return getProductElements().asFixedIterable()
      .stream()
      .map(InventoryItem::new)
      .collect(Collectors.toList());
  }

  public InventoryItem findItemByTitle(String title) {
    return getItems().stream()
      .filter(item -> item.getTitle().equals(title))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Item with title '" + title + "' not found"));
  }

  @Step("Go to cart")
  public CartPage goToCart() {
    $(".shopping_cart_link").click();
    return new CartPage();
  }

  @Override
  protected void prepareIsLoadedCheckpoints() {
    getProductElements()
      .shouldHave(sizeGreaterThan(2))
      .forEach(prod -> prod.shouldBe(visible));
  }
}
