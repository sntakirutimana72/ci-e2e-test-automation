package allure;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class AllureSuccessAndFailureTestListener implements ITestListener {

  @Override
  public void onTestSuccess(ITestResult result) {
    log.info("✓ Test PASSED: {}", result.getMethod().getMethodName());
    addEnvironmentInfo();
  }

  @Override
  public void onTestFailure(ITestResult result) {
    log.info("✗ Test FAILED: {}", result.getMethod().getMethodName());
    addEnvironmentInfo();
  }

  private void addEnvironmentInfo() {
    Allure.parameter("Environment", System.getProperty("env", "test"));
    Allure.parameter("OS", System.getProperty("os.name"));
    Allure.parameter("Java Version", System.getProperty("java.version"));
    Allure.parameter("Timestamp", String.valueOf(System.currentTimeMillis()));
  }
}
