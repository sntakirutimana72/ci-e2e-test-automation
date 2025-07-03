package data;

import org.testng.annotations.DataProvider;

public class AccountDataProvider extends BaseDataProvider {

  @DataProvider(name = "open")
  public static Object[][] valid() {
    return load().account().open();
  }

  @DataProvider(name = "delete")
  public static Object[][] delete() {
    return load().account().delete();
  }

  @DataProvider(name = "defaultCustomer")
  public static Object[][] defaultCustomer() {
    return new Object[][] {{load().account().customer()}};
  }

  @DataProvider(name = "defaultCurrency")
  public static Object[][] defaultCurrency() {
    return new Object[][] {{load().account().currency()}};
  }
}
