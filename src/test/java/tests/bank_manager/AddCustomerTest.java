package tests.bank_manager;

import com.allure.annotations.Preconditions;
import com.allure.annotations.Scenario;
import com.specs.dashboard.AddCustomerPage;
import data.NewCustomerDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddCustomerTest extends BankManagerBaseTest {

  private AddCustomerPage ensureAddCustomerPageIsLoaded() {
    Assert.assertTrue(dashboardPage.isLoaded());
    AddCustomerPage addCustomerPage = dashboardPage.clickAddCustomer();
    Assert.assertTrue(addCustomerPage.isLoaded());
    return addCustomerPage;
  }

  @Test(dataProvider = "validProfile", dataProviderClass = NewCustomerDataProvider.class)
  @Scenario("Add new customer")
  @Preconditions({"Bank app should be accessible on web"})
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if a bank manager is able to add new customers")
  public void verifyManagerCanAddCustomerSuccessfully(String...payload) {
    AddCustomerPage page = ensureAddCustomerPageIsLoaded().add(payload[0], payload[1], payload[2]);
    assertSuccess(page, payload[3]);
  }

  @Test(dataProvider = "invalidNames", dataProviderClass = NewCustomerDataProvider.class)
  @Scenario("Add new customer")
  @Preconditions({"Bank app should be accessible on web"})
  @Severity(SeverityLevel.NORMAL)
  @Description("Verify if customer names are validated when adding new customer")
  public void verifyAddingCustomersWithInvalidNames(String...payload) {
    AddCustomerPage page = ensureAddCustomerPageIsLoaded().add(payload[0], payload[1], payload[2]);
    Assert.assertThrows(() -> assertSuccess(page, payload[3]));
  }

  @Test(dataProvider = "invalidPostalCode", dataProviderClass = NewCustomerDataProvider.class)
  @Scenario("Add new customer")
  @Preconditions({"Bank app should be accessible on web"})
  @Severity(SeverityLevel.NORMAL)
  @Description("Verify if customer postal code is validated when adding new customer")
  public void verifyAddingCustomersWithInvalidPostalCode(String...payload) {
    AddCustomerPage page = ensureAddCustomerPageIsLoaded().add(payload[0], payload[1], payload[2]);
    Assert.assertThrows(() -> assertSuccess(page, payload[3]));
  }
}
