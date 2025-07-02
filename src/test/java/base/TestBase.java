package base;

import com.util.DriverFactory;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Epic("Bank Account System")
public class TestBase {

  protected WebDriver driver;

  @Parameters("browser")
  @BeforeMethod
  public void setup(@Optional("chrome") String browser) {
    switch (browser.toLowerCase()) {
      case "firefox" -> driver = DriverFactory.firefoxDriver();
      case "edge" -> driver = DriverFactory.edgeDriver();
      default -> driver = DriverFactory.chromedriver();
    }
    driver.manage().window().maximize();
  }

  @AfterMethod
  public void teardown() {
    DriverFactory.terminate();
  }

  @Step("Navigate to {url}")
  public void navigateTo(String url) {
    driver.get(url);
  }
}
