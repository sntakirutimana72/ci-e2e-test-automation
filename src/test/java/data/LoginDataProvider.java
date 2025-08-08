package data;

import org.testng.annotations.DataProvider;

public class LoginDataProvider extends BaseDataProvider {

  @DataProvider(name = "successful-login")
  public static Object[][] successfulLogin() {
    return load().login().success().stream()
      .map(e -> new Object[]{ e.desc(), e.request() })
      .toArray(Object[][]::new);
  }

  @DataProvider(name = "invalid-login")
  public static Object[][] unsuccessfulLogin() {
    return load().login().failure().stream()
      .map(e -> new Object[]{ e.desc(), e.request(), e.expected() })
      .toArray(Object[][]::new);
  }
}
