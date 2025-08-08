package tests.checkout;

import com.dto.ExpectedDto;
import com.dto.RequestDto;
import com.page.checkout.CheckoutBillingInfoPage;
import data.CheckoutDataProvider;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class BillingInfoTest extends CheckoutBaseTest {

  private CheckoutBillingInfoPage billingInfoPage;

  @BeforeMethod
  public void initialize() {
    super.initialize();

    // Navigate to the billing info page
    billingInfoPage = cart.checkout();
    assertTrue(billingInfoPage.isLoaded(), "Billing info page is not loaded");
  }

  @Test(dataProvider = "valid-billing-info", dataProviderClass = CheckoutDataProvider.class)
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify filling overview with valid billing info")
  public void verifyFillingOverviewWithValidBillingInfo(RequestDto request) {
    assertTrue(billingInfoPage.fillInfo(request.getFirstname(), request.getLastname(), request.getPostalCode())
      .isLoaded());
  }

  @Test(dataProvider = "invalid-billing-info", dataProviderClass = CheckoutDataProvider.class)
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify billing info is properly validated: {desc}")
  public void verifyBillingInfoValidation(String desc, RequestDto request, ExpectedDto expected) {
    billingInfoPage.fillInfo(request.getFirstname(), request.getLastname(), request.getPostalCode());
    assertTrue(billingInfoPage.hasErrorMessage(expected.getMessage()));
  }
}
