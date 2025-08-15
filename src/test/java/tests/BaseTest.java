package tests;

import com.util.Envs;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.*;

import java.util.function.Supplier;

public abstract class BaseTest {

  @BeforeSuite
  public void setup() {
    // Set base URI for Rest-Assured
    RestAssured.baseURI = Envs.AUT_BASE_URI;

    // Attached allure filter for logging traffic to Rest-Assured
    RestAssured.filters(new AllureRestAssured());
  }

  @Step("{description}")
  static <T> T step(@SuppressWarnings("unused") String description, Supplier<T> action) {
    return action.get();
  }
}