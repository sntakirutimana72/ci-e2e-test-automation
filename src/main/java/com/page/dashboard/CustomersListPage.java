package com.page.dashboard;

import com.page.Base;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CustomersListPage extends Base {

  @FindBy(css = "input[placeholder='Search Customer']")
  private WebElement customerSearch;

  public CustomersListPage(WebDriver driver) {
    super(driver);
  }

  @Step("Enter customer~`{name}` in the search field")
  public void enterCustomer(String name) {
    customerSearch.clear();
    customerSearch.sendKeys(name);
  }

  @Step("Click `Delete` button")
  public void deleteCustomer(WebElement entry) {
    entry.findElement(By.tagName("button")).click();
  }

  public List<WebElement> findCustomer(String name) {
    enterCustomer(name);
    return driver.findElements(By.xpath("//tbody/tr"));
  }

  public boolean isLoaded() {
    try {
      waitForVisibility(By.xpath("//button[contains(., 'Delete')]"), 10);
      return true;
    } catch (Exception e) {
      logger.error(e.getMessage());
      return false;
    }
  }
}
