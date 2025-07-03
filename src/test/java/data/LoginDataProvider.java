package data;

import org.testng.annotations.DataProvider;

public class LoginDataProvider extends BaseDataProvider {

  @DataProvider(name = "withAccount")
  public static Object[][] defaultLogin() {
    return load().login().withAccount();
  }
}
