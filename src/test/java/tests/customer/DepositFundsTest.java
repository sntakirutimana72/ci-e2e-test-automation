package tests.customer;

import com.allure.annotations.Preconditions;
import com.allure.annotations.Scenario;
import com.page.dashboard.DepositPage;
import data.DepositDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DepositFundsTest extends CustomerBaseTestWithPreconditions {

  private DepositPage assurePageIsLoaded(String url, String user) {
    DepositPage depositPage = loginPrecondition(url, user).clickDeposit();
    Assert.assertTrue(depositPage.isLoaded());
    return depositPage;
  }

  @Test(dataProvider = "valid", dataProviderClass = DepositDataProvider.class)
  @Scenario("Making deposits")
  @Preconditions({"Bank app should be accessible on web"})
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if a customer can successfully make a deposit")
  public void verifyCustomerCanDepositSuccessfully(String...payload) {
    Assert.assertTrue(assurePageIsLoaded(payload[0], payload[1])
      .deposit(payload[2])
      .wasSuccessful(payload[3]));
  }

  @Test(dataProvider = "invalid", dataProviderClass = DepositDataProvider.class)
  @Scenario("Making deposits")
  @Preconditions({"Bank app should be accessible on web"})
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if amount gets validated when making deposits")
  public void verifyCustomerCannotDepositWithInvalidAmount(String...payload) {
    Assert.assertFalse(assurePageIsLoaded(payload[0], payload[1])
      .deposit(payload[2])
      .wasSuccessful(payload[3]));
  }
}
