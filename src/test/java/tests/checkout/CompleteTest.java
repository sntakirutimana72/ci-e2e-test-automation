package tests.checkout;

import com.dto.RequestDto;
import com.page.checkout.CheckoutBillingInfoPage;
import com.page.checkout.CheckoutCompletePage;
import com.page.checkout.CheckoutOverviewPage;
import data.CheckoutDataProvider;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CompleteTest extends CheckoutBaseTest {

  private CheckoutCompletePage completePage;

  @BeforeMethod
  public void initialize() {
    super.initialize();

    // Navigate to billing info page first and fill in the required fields
    RequestDto info = (RequestDto) CheckoutDataProvider.validBillingInfo()[0][0];
    CheckoutBillingInfoPage billingInfoPage = cart.checkout();
    assertTrue(billingInfoPage.isLoaded(), "Billing info page is not loaded");

    CheckoutOverviewPage overview = billingInfoPage
      .fillInfo(info.getFirstname(), info.getLastname(), info.getPostalCode());
    assertTrue(overview.isLoaded(), "Overview page is not loaded");

    // Proceed to complete the checkout
    completePage = overview.finishCheckout();
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify checkout completion")
  public void verifyCheckoutCompletion() {
    assertTrue(completePage.isLoaded(), "Checkout complete page is not loaded");
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @Description("Verify back to products functionality")
  public void verifyBackToProducts() {
    assertTrue(completePage.isLoaded(), "Checkout complete page is not loaded");
    assertTrue(completePage.backToProducts().isLoaded());
  }
}
