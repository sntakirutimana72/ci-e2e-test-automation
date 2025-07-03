package tests;

import com.util.DriverFactory;

import com.util.SystemLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.annotations.*;

import java.util.Objects;

@Epic("Bank Account System")
@Feature("Bank Account Management")
public class BaseTest {

  protected WebDriver driver;
  protected Logger logger;

  @BeforeClass
  public void setupLogging() {
    logger = SystemLogger.getLogger(getClass());
  }

  @BeforeMethod
  @Parameters("browser")
  public void setup(@Optional("chrome") String browser) {
    switch (browser.toLowerCase()) {
      case "firefox" -> driver = DriverFactory.firefoxDriver();
      case "edge" -> driver = DriverFactory.edgeDriver();
      default -> driver = DriverFactory.chromedriver();
    }
    driver.manage().window().maximize();
  }

  @Step("Go to {url}")
  public void navigateTo(String url) {
    driver.get(url);
  }

  @AfterMethod
  public void teardown() {
    if (Objects.nonNull(driver))
      driver.quit();
  }
}
