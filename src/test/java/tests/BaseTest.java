package tests;

import com.codeborne.selenide.WebDriverRunner;
import com.util.Envs;
import com.util.GridDriverProvider;
import io.qameta.allure.Step;
import com.codeborne.selenide.Configuration;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public abstract class BaseTest {

  @BeforeSuite
  public void beforeSuite() {
    Configuration.baseUrl = Envs.AUT_BASE_URI;
    Configuration.timeout = 10000;
  }

  @BeforeMethod
  @Parameters({"browser", "version"})
  public void beforeEach(String browser, String version) {
    // Set current thread browser and browser version
    System.setProperty("CURRENT_BROWSER", browser);
    System.setProperty("CURRENT_BROWSER_VERSION", version);

    // Then set the Configuration.browser to use our custom provider
    Configuration.browser = GridDriverProvider.class.getName();

    log.info("Browser configuration set - browser: {}, thread: {}", browser, Thread.currentThread().getId());
  }

  @BeforeMethod(dependsOnMethods = "beforeEach")
  public void beforeEachNavigateToAUT() {
    executeStep(
      "Navigate to " + Configuration.baseUrl,
      () -> open("")
    );
  }

  @AfterMethod(alwaysRun = true)
  public void afterExample() {
    WebDriver driver = WebDriverRunner.getWebDriver();

    // Clear cookies
    driver.manage().deleteAllCookies();

    // Clear localStorage and sessionStorage
    if (driver instanceof JavascriptExecutor js) {
      try {
        js.executeScript("window.localStorage.clear();");
        js.executeScript("window.sessionStorage.clear();");
      } catch (Exception e) {
        log.error("Storage cleanup failed: {}", e.getMessage());
      }
    }

    // Optional: Close all open tabs/windows except main one
    for (String handle : driver.getWindowHandles()) {
      if (!handle.equals(driver.getWindowHandle())) {
        driver.switchTo().window(handle).close();
      }
    }

    // Switch back to main window
    driver.switchTo().window(driver.getWindowHandle());
  }

  @Step("{description}")
  public static void executeStep(@SuppressWarnings("unused") String description, Runnable action) {
    action.run();
  }
}