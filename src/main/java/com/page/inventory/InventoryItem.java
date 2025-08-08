package com.page.inventory;

import com.codeborne.selenide.SelenideElement;
import com.page.elements.BaseItem;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class InventoryItem extends BaseItem {

  public InventoryItem(SelenideElement root) {
    super(root);
  }

  public String getTitle() {
    return findBy("[data-test='inventory-item-name']").getText();
  }

  public String getPrice() {
    return findBy("[data-test='inventory-item-price']").getText();
  }

  public boolean hasRemoveButton() {
    return findByText(".btn_inventory", "Remove").is(visible);
  }

  public boolean hasAddToCartButton() {
    return findByText(".btn_inventory", "Add to cart").is(visible);
  }

  @Step("Add product to cart")
  public InventoryItem addToCart() {
    findByText(".btn_inventory", "Add to cart").click();
    return this;
  }

  @Step("Remove product from cart")
  public void removeFromCart() {
    findByText(".btn_inventory", "Remove").click();
  }
}
