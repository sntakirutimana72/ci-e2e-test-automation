package com.allure.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;
import java.util.Objects;

public class AllureSuccessAndFailureTestListener implements ITestListener {

  @Override
  public void onTestSuccess(ITestResult result) {
    System.out.println("✓ Test PASSED: " + result.getMethod().getMethodName());

    // Capture screenshot on success
    Object testClass = result.getInstance();
    WebDriver driver = getDriverFromTestClass(testClass);

    if (driver != null) {
      byte[] screenshot = takeScreenshot(driver);
      if (screenshot != null) {
        // Attach screenshot to Allure report
        Allure.addAttachment("Success Screenshot", "image/png",
          new ByteArrayInputStream(screenshot), "png");

        // Also save to screenshots directory for CI artifact
        saveScreenshotToFile(screenshot, "success_" + result.getMethod().getMethodName());
      }

      // Add success details
      Allure.addAttachment("Test Result", "✓ PASSED: " + result.getMethod().getMethodName());
      Allure.addAttachment("Page URL", Objects.requireNonNull(driver.getCurrentUrl()));
    }

    addEnvironmentInfo();
  }

  @Override
  public void onTestFailure(ITestResult result) {
    System.out.println("✗ Test FAILED: " + result.getMethod().getMethodName());

    // Capture screenshot on failure
    Object testClass = result.getInstance();
    WebDriver driver = getDriverFromTestClass(testClass);

    if (driver != null) {
      byte[] screenshot = takeScreenshot(driver);
      if (screenshot != null) {
        // Attach screenshot to Allure report
        Allure.addAttachment("Failure Screenshot", "image/png",
          new ByteArrayInputStream(screenshot), "png");

        // Also save to screenshots directory for CI artifact
        saveScreenshotToFile(screenshot, "failure_" + result.getMethod().getMethodName());
      }

      // Add failure details
      Allure.addAttachment("Failure Reason", result.getThrowable().toString());
      Allure.addAttachment("Page URL", Objects.requireNonNull(driver.getCurrentUrl()));

      // Add a page source for debugging
      savePageSource(driver);
    }

    addEnvironmentInfo();
  }

  @Attachment(value = "Screenshot", type = "image/png")
  private byte[] takeScreenshot(WebDriver driver) {
    try {
      return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    } catch (Exception e) {
      System.err.println("Failed to capture screenshot: " + e.getMessage());
      return null;
    }
  }

  @Attachment(value = "Page Source", type = "text/html")
  private void savePageSource(WebDriver driver) {
    try {
      driver.getPageSource();
    } catch (Exception e) {
      System.err.println("Failed to capture page source: " + e.getMessage());
    }
  }

  private void saveScreenshotToFile(byte[] screenshot, String testName) {
    try {
      java.io.File screenshotDir = new java.io.File("screenshots");
      if (!screenshotDir.exists())
        //noinspection ResultOfMethodCallIgnored
        screenshotDir.mkdirs();

      String fileName = testName + "_" + System.currentTimeMillis() + ".png";
      java.io.File screenshotFile = new java.io.File(screenshotDir, fileName);

      java.nio.file.Files.write(screenshotFile.toPath(), screenshot);
      System.out.println("Screenshot saved: " + screenshotFile.getAbsolutePath());
    } catch (Exception e) {
      System.err.println("Failed to save screenshot to file: " + e.getMessage());
    }
  }

  private void addEnvironmentInfo() {
    Allure.parameter("Environment", System.getProperty("env", "test"));
    Allure.parameter("OS", System.getProperty("os.name"));
    Allure.parameter("Java Version", System.getProperty("java.version"));
    Allure.parameter("Timestamp", String.valueOf(System.currentTimeMillis()));
  }

  private WebDriver getDriverFromTestClass(Object testClass) {
    if (testClass instanceof ITestResult)
      return (WebDriver) ((ITestResult) testClass).getAttribute("webdriver");

    // Fallback to reflection for ThreadLocal
    try {
      java.lang.reflect.Field driverField = testClass.getClass().getDeclaredField("driver");
      driverField.setAccessible(true);
      ThreadLocal<WebDriver> driverThreadLocal = (ThreadLocal<WebDriver>) driverField.get(testClass);
      return driverThreadLocal.get();
    } catch (Exception e) {
      System.err.println("Failed to get WebDriver: " + e.getMessage());
      return null;
    }
  }
}
