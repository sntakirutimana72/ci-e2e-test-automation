package tests.customer;

import com.allure.annotations.Preconditions;
import com.allure.annotations.Scenario;
import data.LoginDataProvider;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class TransactionHistoryTest extends CustomerBaseTestWithPreconditions {

  @Test(dataProvider = "withAccount", dataProviderClass = LoginDataProvider.class)
  @Scenario("View transaction history")
  @Preconditions({"Bank app should be accessible on web"})
  @Severity(SeverityLevel.BLOCKER)
  @Description("Verify if customers can view their own transaction history")
  public void verify(String url, String username) {
    Assert.assertTrue(
      loginPrecondition(url, username)
        .clickTransactions()
        .isLoaded()
    );
  }
}
