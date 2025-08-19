package providers;

import org.testng.annotations.DataProvider;

public class AuthDataProvider extends BaseDataProvider {

  @DataProvider(name = "login-success")
  public static Object[][] validLogin() {
    return load().login().valid().stream()
      .map(e -> new Object[]{ e.desc(), e.request(), e.expected() })
      .toArray(Object[][]::new);
  }

  @DataProvider(name = "login-failure")
  public static Object[][] invalidLogin() {
    return load().login().invalid().stream()
      .map(e -> new Object[]{ e.desc(), e.request(), e.expected() })
      .toArray(Object[][]::new);
  }
}
