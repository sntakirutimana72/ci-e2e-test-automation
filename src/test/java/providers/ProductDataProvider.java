package providers;

import org.testng.annotations.DataProvider;

public class ProductDataProvider extends BaseDataProvider {

  @DataProvider(name = "create-valid")
  public static Object[][] createValid() {
    return load().products()
      .create()
      .valid()
      .stream()
      .map(e -> new Object[] { e.request(), e.expected() })
      .toArray(Object[][]::new);
  }

  @DataProvider(name = "create-invalid")
  public static Object[][] createInvalid() {
    return load().products()
      .create()
      .invalid()
      .stream()
      .map(e -> new Object[] { e.desc(), e.request(), e.expected() })
      .toArray(Object[][]::new);
  }

  @DataProvider(name = "patch-valid")
  public static Object[][] patchValid() {
    return load().products()
      .patch()
      .valid()
      .stream()
      .map(e -> new Object[] { e.request(), e.expected() })
      .toArray(Object[][]::new);
  }

  @DataProvider(name = "patch-invalid")
  public static Object[][] patchInvalid() {
    return load().products()
      .patch()
      .invalid()
      .stream()
      .map(e -> new Object[] { e.desc(), e.request(), e.expected() })
      .toArray(Object[][]::new);
  }
}
