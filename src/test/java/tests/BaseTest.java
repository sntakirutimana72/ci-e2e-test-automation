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

  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
  protected Logger logger;

  @BeforeClass
  public void setupLogging() {
    logger = SystemLogger.getLogger(getClass());
  }

  @BeforeMethod
  @Parameters("browser")
  public void setup(@Optional("chrome") String browser) {
    switch (browser.toLowerCase()) {
      case "firefox" -> driver.set(DriverFactory.firefoxDriver());
      case "edge" -> driver.set(DriverFactory.edgeDriver());
      default -> driver.set(DriverFactory.chromedriver());
    }
    getDriver().manage().window().maximize();
  }

  @Step("Go to {url}")
  public void navigateTo(String url) {
    getDriver().get(url);
  }

  @AfterMethod
  public void teardown() {
    if (Objects.nonNull(getDriver())) {
      getDriver().quit();
      driver.remove();
    }
  }

  protected WebDriver getDriver() {
    return driver.get();
  }
}
