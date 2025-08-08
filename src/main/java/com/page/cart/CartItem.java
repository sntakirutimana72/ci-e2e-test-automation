package com.page.cart;

import com.codeborne.selenide.SelenideElement;
import com.page.elements.BaseItem;

public class CartItem extends BaseItem {

  public CartItem(SelenideElement root) {
    super(root);
  }

  public String getTitle() {
    return findBy("[data-test='inventory-item-name']").getText();
  }

  public String getPrice() {
    return findBy("[data-test='inventory-item-price']").getText();
  }

  public void remove() {
    findByText(".cart_button", "Remove").click();
  }
}
