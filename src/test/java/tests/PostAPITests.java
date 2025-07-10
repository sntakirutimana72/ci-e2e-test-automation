package tests;

import com.api.PostAPI;
import data.providers.PostDataProvider;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostAPITests extends BaseTest {

  private void assertContainsAllEntries(ValidatableResponse response, Object rawPayload) {
    @SuppressWarnings("unchecked")
    Map<String, Object> payload = (HashMap<String, Object>) rawPayload;
    @SuppressWarnings("unchecked")
    Map<String, Object> responseMap = response.extract().as(Map.class);

    for (Map.Entry<String, Object> entry : payload.entrySet())
      assertThat(responseMap, hasEntry(entry.getKey(), entry.getValue()));
  }

  @Test(dataProvider = "createPost", dataProviderClass = PostDataProvider.class)
  public void verifyUserCanCreatePostSuccessfully(Object payload, int expectedStatus) {
    ValidatableResponse response = PostAPI.createPost(payload, expectedStatus)
      .then()
      .body("$", hasKey("id"));
    assertContainsAllEntries(response, payload);
  }

  @Test(dataProvider = "updatePost", dataProviderClass = PostDataProvider.class)
  public void verifyUserCanUpdatePostSuccessfully(int id, Object payload, int expectedStatus) {
    ValidatableResponse response = PostAPI.updatePost(id, payload, expectedStatus)
      .then()
      .body("$", hasKey("id"))
      .body("id", equalTo(id));
    assertContainsAllEntries(response, payload);
  }

  @Test(dataProvider = "getPostById", dataProviderClass = PostDataProvider.class)
  public void verifyUserCanRetrievePostById(int id, int expectedStatus) {
    PostAPI.getPostById(id, expectedStatus)
      .then()
      .body("$", hasKey("id"))
      .body("id", equalTo(id))
      .body("$", hasKey("userId"));
  }

  @Test(dataProvider = "getPostByWrongId", dataProviderClass = PostDataProvider.class)
  public void verifyUserCannotRetrievePostProvidedWrongId(int id, int expectedStatus) {
    PostAPI.getPostById(id, expectedStatus);
  }

  @Test
  public void verifyUserCanRetrieveAllPosts() {
    PostAPI.getPosts(200)
      .then()
      .body("size()", greaterThan(0))
      .body("[0]", hasKey("id"))
      .body("[0]", hasKey("title"))
      .body("[0]", hasKey("userId"));
  }

  @Test(dataProvider = "getPostById", dataProviderClass = PostDataProvider.class)
  public void verifyUserCanSeeAllCommentsOfPost(int id, int expectedStatus) {
    PostAPI.getCommentsForPost(id, expectedStatus)
      .then()
      .body("size()", greaterThan(0))
      .body("[0].postId", equalTo(id));
  }

  @Test(dataProvider = "deletePost", dataProviderClass = PostDataProvider.class)
  public void verifyUserCanDeletePostSuccessfully(int id, int expectedStatus) {
    PostAPI.deletePost(id, expectedStatus);
  }
}
