package tests.customer;

import com.allure.annotations.Preconditions;
import com.page.dashboard.CustomerDashboardPage;
import com.page.login.CustomerLoginPage;
import io.qameta.allure.Story;
import org.testng.Assert;
import tests.BaseTest;

@Story("""
  As a Customer,
  I want to view my transactions, deposit funds,
  and withdraw money so that I can manage my finances effectively.
""")
public class CustomerBaseTestWithPreconditions extends BaseTest {

  @Preconditions(value = {"Customer should be logged in"}, steps = {
    "Navigate to https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer",
    "Select a customer",
    "Click `Login` button"
  })
  public CustomerDashboardPage loginPrecondition(String url, String username) {
    CustomerLoginPage loginPage = new CustomerLoginPage(driver);
    navigateTo(url);
    CustomerDashboardPage dashboardPage = loginPage.login(username);
    Assert.assertTrue(dashboardPage.isLoaded(username));
    return dashboardPage;
  }
}
