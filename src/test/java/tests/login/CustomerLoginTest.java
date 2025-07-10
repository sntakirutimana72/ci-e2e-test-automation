package tests.login;

import data.LoginDataProvider;
import tests.BaseTest;
import com.allure.annotations.Preconditions;
import com.allure.annotations.Scenario;
import com.specs.login.CustomerLoginPage;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.Assert;

@Story("""
  As a Customer,
  I want to view my transactions, deposit funds,
  and withdraw money so that I can manage my finances effectively.
""")
public class CustomerLoginTest extends BaseTest {

  private CustomerLoginPage loginPage;

  @BeforeMethod
  public void initialize() {
    loginPage = new CustomerLoginPage(getDriver());
  }

  @Test(dataProvider = "withAccount", dataProviderClass = LoginDataProvider.class)
  @Scenario("Account access")
  @Preconditions({"Bank app should be accessible on web"})
  @Severity(SeverityLevel.BLOCKER)
  @Description("Verify if customer can successfully access their own account")
  public void verifyCustomerCanAccessTheirOwnAccount(String url, String username) {
    navigateTo(url);
    Assert.assertTrue(loginPage.login(username).isLoaded(username));
  }
}
