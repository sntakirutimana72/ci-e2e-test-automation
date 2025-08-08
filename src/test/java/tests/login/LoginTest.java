package tests.login;

import com.dto.ExpectedDto;
import com.dto.RequestDto;
import com.page.LoginPage;
import data.LoginDataProvider;
import tests.BaseTest;

import io.qameta.allure.testng.Tag;
import io.qameta.allure.*;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

  private LoginPage loginPage;

  @BeforeMethod
  public void initialize() {
    loginPage = new LoginPage();
  }

  @Test(dataProvider = "successful-login", dataProviderClass = LoginDataProvider.class)
  @Severity(SeverityLevel.BLOCKER)
  @Tag("login")
  @Description("Verify login with valid credentials: {desc}")
  public void verifyLoginWithValidCredentials(String desc, RequestDto request) {
    assertTrue(loginPage.isLoaded());
    loginPage.login(request.getUsername(), request.getPassword());
  }

  @Test(dataProvider = "invalid-login", dataProviderClass = LoginDataProvider.class)
  @Severity(SeverityLevel.CRITICAL)
  @Tag("login")
  @Description("Verify login with invalid credentials: {desc}")
  public void verifyLoginWithInvalidCredentials(String desc, RequestDto request, ExpectedDto expected) {
    assertTrue(loginPage.isLoaded());
    loginPage.login(request.getUsername(), request.getPassword());
    assertTrue(loginPage.hasErrorMessage(expected.getMessage()));
  }
}
