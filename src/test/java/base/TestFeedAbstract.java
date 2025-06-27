package base;

import com.page.FeedPage;
import com.pojo.RootData;
import com.pojo.feed.FeedData;
import com.util.YamlDataLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestFeedAbstract extends TestBase {

  protected FeedPage feedPage;
  protected static FeedData feedData;

  @BeforeEach
  public void prepare() {
    feedPage = new FeedPage(driver);
  }

  @BeforeAll
  public static void prepareTestData() {
    RootData rootData = YamlDataLoader.loadTestData();
    feedData = rootData.feed();
  }

  @AfterAll
  public static void cleanup() {
    feedData = null;
  }
}
