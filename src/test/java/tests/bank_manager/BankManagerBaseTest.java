package tests.bank_manager;

import com.page.Base;
import com.page.dashboard.BankManagerDashboardPage;
import io.qameta.allure.Story;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import tests.BaseTest;

@Story("""
  As a Bank Manager,
  I want to add customers, create accounts,
  and delete accounts so that I can manage customer accounts efficiently.
""")
public abstract class BankManagerBaseTest extends BaseTest {

  protected BankManagerDashboardPage dashboardPage;

  @BeforeMethod(dependsOnMethods = "setup")
  public void initialize() {
    navigateTo("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
    dashboardPage = new BankManagerDashboardPage(getDriver());
  }

  protected void assertSuccess(Base page, String message) {
    Alert alert = page.waitForAlertPresence(10);
    Assert.assertTrue(alert.getText().contains(message));
    alert.dismiss();
  }
}
