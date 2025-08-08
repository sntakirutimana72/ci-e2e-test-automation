package util;

import com.page.LoginPage;
import com.page.inventory.InventoryPage;
import com.util.Envs;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.testng.AssertJUnit.assertTrue;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {

  public static InventoryPage signIn() {
    LoginPage loginPage = new LoginPage();
    assertTrue(loginPage.isLoaded());
    return loginPage.login(Envs.get("STANDARD_USER"), Envs.get("ANY_USER_PASS"));
  }
}
