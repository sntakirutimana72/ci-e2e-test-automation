package com.page.modal;

import com.page.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Subscribed extends Base {

  @FindBy(how = How.ID, using = "success-modal")
  private WebElement successModal;

  @FindBy(how = How.CLASS_NAME, using = "dismiss-btn")
  private WebElement dismissButton;

  public Subscribed(WebDriver driver) {
    super(driver);
  }

  public boolean isSuccessModalVisible() {
    try
    {
      waitForElementToAppear(successModal);
      return successModal.isDisplayed();
    }
    catch (TimeoutException ignored)
    {
      return false;
    }
  }

  public boolean isSuccessModalAbsent() {
    try
    {
      waitForElementToDisappear(successModal);
      return true;
    }
    catch (TimeoutException ignored)
    {
      return false;
    }
  }

  public void dismiss() {
    dismissButton.click();
  }

  public void dismissUsingSpace() {
    moveTo(dismissButton);
    dismissButton.sendKeys(Keys.SPACE);
  }

  public void dismissUsingEnter() {
    moveTo(dismissButton);
    dismissButton.sendKeys(Keys.ENTER);
  }
}
