package com.page.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Condition.text;

@AllArgsConstructor
public abstract class BaseItem {

  private final SelenideElement root;

  public boolean isDisplayed() {
    return getRoot().isDisplayed();
  }

  protected SelenideElement getRoot() {
    return root;
  }

  protected SelenideElement findByText(String selector, String partialText) {
    return getRoot().$$(selector)
      .filterBy(text(partialText))
      .first();
  }

  protected SelenideElement findBy(String selector) {
    return getRoot().$(selector);
  }
}
