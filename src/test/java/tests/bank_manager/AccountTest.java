package tests.bank_manager;

import com.allure.annotations.Preconditions;
import com.allure.annotations.Scenario;
import com.page.dashboard.AddCustomerPage;
import com.page.dashboard.CustomersListPage;
import com.page.dashboard.OpenAccountPage;
import data.AccountDataProvider;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AccountTest extends BankManagerBaseTest {

  private OpenAccountPage ensureOpenAccountIsLoaded() {
    Assert.assertTrue(dashboardPage.isLoaded());
    OpenAccountPage openAccountPage = dashboardPage.clickOpenAccount();
    Assert.assertTrue(openAccountPage.isLoaded());
    return openAccountPage;
  }

  private void ensureAccountIsOpened(String...payload) {
    Assert.assertTrue(dashboardPage.isLoaded());

    AddCustomerPage addCustomerPage = dashboardPage.clickAddCustomer();
    Assert.assertTrue(addCustomerPage.isLoaded());
    addCustomerPage.add(payload[0], payload[1], payload[2]);
    assertSuccess(addCustomerPage, payload[4]);

    OpenAccountPage openAccountPage = dashboardPage.clickOpenAccount();
    Assert.assertTrue(openAccountPage.isLoaded());
    openAccountPage.process(String.format("%s %s", payload[0], payload[1]), payload[3]);
    assertSuccess(openAccountPage, payload[5]);
  }

  @Test(dataProvider = "open", dataProviderClass = AccountDataProvider.class)
  @Scenario("Open a customer account")
  @Preconditions({"Bank app should be accessible on web"})
  public void verifyManagerCanOpenAccountSuccessfully(String...payload) {
    ensureAccountIsOpened(payload);
  }

  @Test(dataProvider = "delete", dataProviderClass = AccountDataProvider.class)
  @Scenario("Delete a customer account")
  @Preconditions({"Bank app should be accessible on web"})
  public void verifyBankManagerCanDeleteCustomerAccount(String...payload) {
    ensureAccountIsOpened(payload);

    CustomersListPage customersListPage = dashboardPage.clickCustomers();
    Assert.assertTrue(customersListPage.isLoaded());

    List<WebElement> customerEntries = customersListPage.findCustomer(payload[1]);
    Assert.assertEquals(customerEntries.size(), 1);
    Assert.assertTrue(customerEntries.get(0).getText().contains(payload[1]));

    customersListPage.deleteCustomer(customerEntries.get(0));

    List<WebElement> customerEntriesAfterDeletion = customersListPage.findCustomer(payload[1]);
    Assert.assertEquals(customerEntriesAfterDeletion.size(), 0);
  }

  @Test(dataProvider = "defaultCurrency", dataProviderClass = AccountDataProvider.class)
  @Scenario("Customer is mandatory to open account")
  @Preconditions({"Bank app should be accessible on web"})
  public void verifyCustomerIsMandatoryToOpenAccount(String currency) {
    OpenAccountPage openAccountPage = ensureOpenAccountIsLoaded();
    openAccountPage.selectCurrency(currency);
    openAccountPage.submit();
    Assert.assertThrows(() -> openAccountPage.waitForAlertPresence(5));
  }

  @Test(dataProvider = "defaultCustomer", dataProviderClass = AccountDataProvider.class)
  @Scenario("Currency is mandatory to open account")
  @Preconditions({"Bank app should be accessible on web"})
  public void verifyCurrencyIsMandatoryToOpenAccount(String customer) {
    OpenAccountPage openAccountPage = ensureOpenAccountIsLoaded();
    openAccountPage.selectCustomer(customer);
    openAccountPage.submit();
    Assert.assertThrows(() -> openAccountPage.waitForAlertPresence(5));
  }
}
