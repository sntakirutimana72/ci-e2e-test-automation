package com.page.dashboard;

import com.page.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerTransactionHistoryPage extends Base {

  @FindBy(id = "anchor0")
  private WebElement firstTransactionEntry;

  public CustomerTransactionHistoryPage(WebDriver driver) {
    super(driver);
  }

  public boolean isLoaded() {
    try {
      waitForVisibility(firstTransactionEntry, 5);
      return true;
    } catch (Exception e) {
      logger.error(e.getMessage());
      return false;
    }
  }
}
