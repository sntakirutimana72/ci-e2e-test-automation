package tests.auth;

import com.apis.AuthApi;
import com.dto.ExpectedResponseDto;
import com.dto.RequestDto;
import io.restassured.response.Response;
import providers.AuthDataProvider;
import tests.BaseTest;

import io.qameta.allure.testng.Tag;
import io.qameta.allure.*;
import org.testng.annotations.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Feature("Authentication")
@Story("TRA-1: As a User, I want to be able to log in with credentials and receive an access token")
@Tag("Scenario: Verify user can log in with credentials & get access token")
@Severity(SeverityLevel.BLOCKER)
@Owner("QA")
public class LoginTest extends BaseTest {
  private final AuthApi authApi = new AuthApi();

  @Test(dataProvider = "login-success", dataProviderClass = AuthDataProvider.class)
  @Severity(SeverityLevel.BLOCKER)
  @Description("Verify {desc} can log in successfully with valid credentials")
  void verifySuccessfulLogin(String desc, RequestDto request, ExpectedResponseDto expected) {
    authApi.signIn(request.asLogin(), expected.getStatus())
      .then()
      .body(matchesJsonSchemaInClasspath(expected.getSchema()));
  }

  @Test(dataProvider = "login-failure", dataProviderClass = AuthDataProvider.class)
  @Severity(SeverityLevel.BLOCKER)
  @Description("Verify login fails with {desc}")
  void verifyLoginFailures(String desc, RequestDto request, ExpectedResponseDto expected) {
    Response resp = authApi.signInWithInvalidCredentials(request.asLogin(), expected.getStatus());

    assertThat("Cookies should be empty", resp.getCookies(), is(anEmptyMap()));
    assertThat("Authorization header should be absent", resp.getHeaders().hasHeaderWithName("Authorization"), is(false));
    assertThat("Response body should match expected", resp.getBody().asString(), equalTo(expected.getMessage()));
  }
}
