package data;

import org.testng.annotations.DataProvider;

public class DepositDataProvider extends BaseDataProvider {

  @DataProvider(name = "valid")
  public static Object[][] valid() {
    return load().transact().deposit().valid();
  }

  @DataProvider(name = "invalid")
  public static Object[][] invalid() {
    return load().transact().deposit().invalid();
  }
}
