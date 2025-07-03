package tests.customer;

import com.allure.annotations.Preconditions;
import com.allure.annotations.Scenario;
import com.page.dashboard.WithdrawalPage;
import data.WithdrawDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WithdrawFundsTest extends CustomerBaseTestWithPreconditions {

  private WithdrawalPage assurePageIsLoaded(String url, String user) {
    WithdrawalPage withdrawalPage = loginPrecondition(url, user).clickWithdrawal();
    Assert.assertTrue(withdrawalPage.isLoaded());
    return withdrawalPage;
  }

  @Test(dataProvider = "valid", dataProviderClass = WithdrawDataProvider.class)
  @Scenario("A customer should be able to withdraw")
  @Preconditions({"Bank app should be accessible on web"})
  public void verifyCustomerCanWithdrawSuccessfully(String...payload) {
    Assert.assertTrue(assurePageIsLoaded(payload[0], payload[1])
      .withdraw(payload[2])
      .wasSuccessful(payload[3]));
  }

  @Test(dataProvider = "invalid", dataProviderClass = WithdrawDataProvider.class)
  @Scenario("A customer should not be able to withdraw when amount is invalid")
  @Preconditions({"Bank app should be accessible on web"})
  public void verifyCustomerCannotWithdrawWithInvalidAmount(String...payload) {
    Assert.assertFalse(assurePageIsLoaded(payload[0], payload[1])
      .withdraw(payload[2])
      .wasSuccessful(payload[3]));
  }
}
