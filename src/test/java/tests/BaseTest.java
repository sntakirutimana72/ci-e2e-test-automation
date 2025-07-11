package tests;

import io.restassured.RestAssured;
import io.qameta.allure.restassured.AllureRestAssured;
import org.slf4j.Logger;
import org.testng.annotations.BeforeSuite;
import util.SystemLogger;

public abstract class BaseTest {

  protected Logger logger;

  @BeforeSuite
  public void setup() {
    logger = SystemLogger.getLogger(getClass());
    RestAssured.filters(new AllureRestAssured());
  }
}
