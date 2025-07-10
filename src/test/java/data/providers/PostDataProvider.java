package data.providers;

import org.testng.annotations.DataProvider;

public class PostDataProvider extends BaseDataProvider {

  @DataProvider(name = "deletePost")
  public static Object[][] deletePost() {
    return load().posts().delete().stream()
      .map(tc -> new Object[]{ tc.id(), tc.expectedStatus() })
      .toArray(Object[][]::new);
  }

  @DataProvider(name = "getPostByWrongId")
  public static Object[][] getPostByWrongId() {
    return load().posts().retrieve().nonExisting().stream()
      .map(tc -> new Object[]{ tc.id(), tc.expectedStatus() })
      .toArray(Object[][]::new);
  }

  @DataProvider(name = "getPostById")
  public static Object[][] getPostById() {
    return load().posts().retrieve().existing().stream()
      .map(tc -> new Object[]{ tc.id(), tc.expectedStatus() })
      .toArray(Object[][]::new);
  }

  @DataProvider(name = "updatePost")
  public static Object[][] updatePost() {
    return load().posts().update().stream()
      .map(tc -> new Object[]{ tc.id(), tc.payload(), tc.expectedStatus() })
      .toArray(Object[][]::new);
  }

  @DataProvider(name = "createPost")
  public static Object[][] createPost() {
    return load().posts().create().stream()
      .map(tc -> new Object[]{ tc.payload(), tc.expectedStatus() })
      .toArray(Object[][]::new);
  }
}
