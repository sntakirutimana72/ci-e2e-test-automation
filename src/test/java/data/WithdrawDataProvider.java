package data;

import org.testng.annotations.DataProvider;

public class WithdrawDataProvider extends BaseDataProvider {

  @DataProvider(name = "valid")
  public static Object[][] valid() {
    return load().transact().withdraw().valid();
  }

  @DataProvider(name = "invalid")
  public static Object[][] invalid() {
    return load().transact().withdraw().invalid();
  }
}
