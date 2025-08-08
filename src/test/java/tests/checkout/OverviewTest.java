package tests.checkout;

import com.dto.RequestDto;
import com.page.checkout.CheckoutBillingInfoPage;
import com.page.checkout.CheckoutOverviewPage;
import data.CheckoutDataProvider;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OverviewTest extends CheckoutBaseTest {

  private CheckoutOverviewPage overview;

  @BeforeMethod
  public void initialize() {
    super.initialize();

    // Navigate to billing info page first and fill in the required fields
    RequestDto info = (RequestDto) CheckoutDataProvider.validBillingInfo()[0][0];
    CheckoutBillingInfoPage billingInfoPage = cart.checkout();
    assertTrue(billingInfoPage.isLoaded(), "Billing info page is not loaded");

    overview = billingInfoPage
      .fillInfo(info.getFirstname(), info.getLastname(), info.getPostalCode());
    assertTrue(overview.isLoaded(), "Overview page is not loaded");
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify the overview page displays correct billing info and totals")
  public void verifyCorrectBillingTotalsAreDisplayed() {
    assertEquals(overview.getSubtotal(), overview.getComputeSubtotal());
    assertEquals(overview.getTotal(), overview.getComputedTotal());
  }
}
