package tests;

import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import util.SystemLogger;

public abstract class BaseTest {

  protected Logger logger;

  @BeforeClass
  public void setup() {
    logger = SystemLogger.getLogger(getClass());
  }
}
