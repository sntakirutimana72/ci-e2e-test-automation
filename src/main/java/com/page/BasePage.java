package com.page;

import com.page.cart.CartCountBadge;

import java.time.Duration;
import java.time.Instant;
import java.util.function.BooleanSupplier;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {

  public boolean hasElementWithText(String selector, String message) {
    return $(selector).has(text(message));
  }

  public boolean waitForElementWithText(String selector, String message) {
    return $(selector).shouldHave(text(message)).exists();
  }

  public CartCountBadge getCartCountBadge() {
    return new CartCountBadge($("span[data-test='shopping-cart-badge']"));
  }

  public final boolean isLoaded() {
    try {
      prepareIsLoadedCheckpoints();
      return true;
    } catch (Throwable e) {
      return false;
    }
  }

  protected abstract void prepareIsLoadedCheckpoints();

  public static void waitWithin(BooleanSupplier condition, int timeoutInSeconds) {
    Instant start = Instant.now();
    Duration timeout = Duration.ofSeconds(timeoutInSeconds);

    while (Duration.between(start, Instant.now()).compareTo(timeout) < 0) {
      try {
        if (condition.getAsBoolean()) return;
      } catch (Throwable ignored) {}

      try {
        //noinspection BusyWait
        Thread.sleep(200);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Wait was interrupted", e);
      }
    }
    throw new AssertionError("Condition not met within " + timeoutInSeconds + " seconds");
  }
}
