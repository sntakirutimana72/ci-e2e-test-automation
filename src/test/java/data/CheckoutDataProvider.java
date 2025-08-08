package data;

import org.testng.annotations.DataProvider;

public class CheckoutDataProvider extends BaseDataProvider {

  @DataProvider(name = "valid-billing-info")
  public static Object[][] validBillingInfo() {
    return load().checkout().billingInfo().valid().stream()
      .map(e -> new Object[]{ e.request() })
      .toArray(Object[][]::new);
  }

  @DataProvider(name = "invalid-billing-info")
  public static Object[][] invalidBillingInfo() {
    return load().checkout().billingInfo().invalid().stream()
      .map(e -> new Object[]{ e.desc(), e.request(), e.expected() })
      .toArray(Object[][]::new);
  }
}
