package com.page.cart;

import com.codeborne.selenide.SelenideElement;
import com.page.elements.BaseItem;

public class CartCountBadge extends BaseItem {

  public CartCountBadge(SelenideElement root) {
    super(root);
  }

  public int getCount() {
    return Integer.parseInt(getRoot().getText());
  }
}
