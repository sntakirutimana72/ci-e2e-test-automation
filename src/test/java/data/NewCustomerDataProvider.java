package data;

import org.testng.annotations.DataProvider;

public class NewCustomerDataProvider extends BaseDataProvider {

  @DataProvider(name = "validProfile")
  public static Object[][] validProfile() {
    return load().newCustomer().valid();
  }

  @DataProvider(name = "invalidNames")
  public static Object[][] invalidNames() {
    return load().newCustomer().invalidNames();
  }

  @DataProvider(name = "invalidPostalCode")
  public static Object[][] invalidPostalCode() {
    return load().newCustomer().invalidPostalCode();
  }
}
