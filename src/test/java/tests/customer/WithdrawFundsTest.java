package tests.customer;

import com.allure.annotations.Preconditions;
import com.allure.annotations.Scenario;
import com.specs.dashboard.WithdrawalPage;
import data.WithdrawDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WithdrawFundsTest extends CustomerBaseTestWithPreconditions {

  private WithdrawalPage assurePageIsLoaded(String url, String user) {
    WithdrawalPage withdrawalPage = loginPrecondition(url, user).clickWithdrawal();
    Assert.assertTrue(withdrawalPage.isLoaded());
    return withdrawalPage;
  }

  @Test(dataProvider = "valid", dataProviderClass = WithdrawDataProvider.class)
  @Scenario("Withdrawing funds")
  @Preconditions({"Bank app should be accessible on web"})
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if customers can successfully withdraw their funds")
  public void verifyCustomerCanWithdrawSuccessfully(String...payload) {
    Assert.assertTrue(assurePageIsLoaded(payload[0], payload[1])
      .withdraw(payload[2])
      .wasSuccessful(payload[3]));
  }

  @Test(dataProvider = "invalid", dataProviderClass = WithdrawDataProvider.class)
  @Scenario("Withdrawing funds")
  @Preconditions({"Bank app should be accessible on web"})
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if amount gets validated when withdrawing")
  public void verifyCustomerCannotWithdrawWithInvalidAmount(String...payload) {
    Assert.assertFalse(assurePageIsLoaded(payload[0], payload[1])
      .withdraw(payload[2])
      .wasSuccessful(payload[3]));
  }
}
