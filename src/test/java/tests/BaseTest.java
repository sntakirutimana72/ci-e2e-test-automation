package tests;

import com.util.DriverFactory;

import com.util.SystemLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Epic("Bank Account System")
@Feature("Bank Account Management")
public class BaseTest {

  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
  protected Logger logger;

  @BeforeClass
  public void setupLogging() {
    logger = SystemLogger.getLogger(getClass());
  }

  @Parameters("browser")
  @BeforeMethod
  public void setup(String browser) {
    switch (browser.toLowerCase()) {
      case "firefox" -> driver.set(DriverFactory.firefoxDriver());
      case "edge" -> driver.set(DriverFactory.edgeDriver());
      default -> driver.set(DriverFactory.chromedriver());
    }
    getDriver().manage().window().maximize();
  }

  @BeforeMethod(dependsOnMethods = "setup")
  public void storeDriverInResult(ITestResult result) {
    result.setAttribute("webdriver", getDriver());
  }

  @Step("Go to {url}")
  public void navigateTo(String url) {
    getDriver().get(url);
  }

  @AfterMethod
  public void teardown() {
    WebDriver currentDriver = driver.get();
    if (currentDriver != null) {
      try {
        currentDriver.manage().deleteAllCookies();
        currentDriver.quit();
      } catch (Exception e) {
        logger.error("Error during driver cleanup: {}", e.getMessage());
      } finally {
        driver.remove();
      }
    }
  }

  @AfterClass
  public void cleanupThreadLocal() {
    driver.remove();
  }

  protected WebDriver getDriver() {
    return driver.get();
  }
}
