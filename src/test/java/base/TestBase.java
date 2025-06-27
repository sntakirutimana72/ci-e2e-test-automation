package base;

import com.util.DriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class TestBase {

  protected WebDriver driver;

  @BeforeEach
  public void setup() {
    driver = DriverFactory.chromedriver();

    driver.get("https://sntakirutimana72.github.io/newsletter-lab1/");
    driver.manage().window().maximize();
  }

  @AfterEach
  public void teardown() {
    driver = null;
    DriverFactory.terminate();
  }
}
