package tests.bank_manager;

import com.allure.annotations.Preconditions;
import com.allure.annotations.Scenario;
import com.page.dashboard.AddCustomerPage;
import data.NewCustomerDataProvider;
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
  @Scenario("A bank manager should be able to add new customers")
  @Preconditions({"Bank app should be accessible on web"})
  public void verifyManagerCanAddCustomerSuccessfully(String...payload) {
    AddCustomerPage page = ensureAddCustomerPageIsLoaded().add(payload[0], payload[1], payload[2]);
    assertSuccess(page, payload[3]);
  }

  @Test(dataProvider = "invalidNames", dataProviderClass = NewCustomerDataProvider.class)
  @Scenario("A bank manager should not be able to add customer when names are invalid")
  @Preconditions({"Bank app should be accessible on web"})
  public void verifyAddingCustomersWithInvalidNames(String...payload) {
    AddCustomerPage page = ensureAddCustomerPageIsLoaded().add(payload[0], payload[1], payload[2]);
    Assert.assertThrows(() -> assertSuccess(page, payload[3]));
  }

  @Test(dataProvider = "invalidPostalCode", dataProviderClass = NewCustomerDataProvider.class)
  @Scenario("A bank manager should not be able to add customer when postal code is invalid")
  @Preconditions({"Bank app should be accessible on web"})
  public void verifyAddingCustomersWithInvalidPostalCode(String...payload) {
    AddCustomerPage page = ensureAddCustomerPageIsLoaded().add(payload[0], payload[1], payload[2]);
    Assert.assertThrows(() -> assertSuccess(page, payload[3]));
  }
}
