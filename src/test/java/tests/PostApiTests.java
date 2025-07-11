package tests;

import com.api.PostApi;
import data.providers.PostDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Feature("Post CRUD API")
public class PostApiTests extends BaseTest {

  private void assertContainsAllEntries(ValidatableResponse response, Object rawPayload) {
    @SuppressWarnings("unchecked")
    Map<String, Object> payload = (HashMap<String, Object>) rawPayload;
    @SuppressWarnings("unchecked")
    Map<String, Object> responseMap = response.extract().as(Map.class);

    for (Map.Entry<String, Object> entry : payload.entrySet())
      assertThat(responseMap, hasEntry(entry.getKey(), entry.getValue()));
  }

  @Test(dataProvider = "createPost", dataProviderClass = PostDataProvider.class)
  @Tag("Scenario: Create a post")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if user can create a post successfully given all required data")
  public void verifyCreatePostSuccessGivenRequiredData(Object payload, int expectedStatus) {
    ValidatableResponse response = PostApi.createPost(payload, expectedStatus)
      .then()
      .body("$", hasKey("id"));
    assertContainsAllEntries(response, payload);
  }

  @Test(dataProvider = "createPost422", dataProviderClass = PostDataProvider.class)
  @Tag("Scenario: Create a post")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if schema validation enforces required fields constraint when creating a post")
  public void verifySchemaValidationWhenCreatingPost(Object payload, int expectedStatus) {
    PostApi.createPost(payload, expectedStatus);
  }

  @Test(dataProvider = "updatePost", dataProviderClass = PostDataProvider.class)
  @Tag("Scenario: Update a post")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if user can update a post successfully given all required data")
  public void verifyUpdatePostSuccessGivenRequiredData(int id, Object payload, int expectedStatus) {
    ValidatableResponse response = PostApi.updatePost(id, payload, expectedStatus)
      .then()
      .body("$", hasKey("id"))
      .body("id", equalTo(id));
    assertContainsAllEntries(response, payload);
  }

  @Test(dataProvider = "updatePost404", dataProviderClass = PostDataProvider.class)
  @Tag("Scenario: Update a post")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if user cannot perform an update on a non-existing post")
  public void verifyUpdatingNonExistingPostFails(int id, Object payload, int expectedStatus) {
    PostApi.updatePost(id, payload, expectedStatus);
  }

  @Test(dataProvider = "getPostById", dataProviderClass = PostDataProvider.class)
  @Tag("Scenario: View posts")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if user can view specific post")
  public void verifyGetPostByIdReturnsTheExpectedPost(int id, int expectedStatus) {
    PostApi.getPostById(id, expectedStatus)
      .then()
      .body("$", hasKey("id"))
      .body("id", equalTo(id))
      .body("$", hasKey("userId"));
  }

  @Test(dataProvider = "getPostByWrongId", dataProviderClass = PostDataProvider.class)
  @Tag("Scenario: View posts")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if correct status code is returned when requested post is not found")
  public void verifyFetchingNonExistingPostReturns404(int id, int expectedStatus) {
    PostApi.getPostById(id, expectedStatus);
  }

  @Test
  @Tag("Scenario: View posts")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if user can view all posts")
  public void verifyGetPostsReturnsAllPosts() {
    PostApi.getPosts(200)
      .then()
      .body("size()", greaterThan(0))
      .body("[0]", hasKey("id"))
      .body("[0]", hasKey("title"))
      .body("[0]", hasKey("userId"));
  }

  @Test(dataProvider = "getPostById", dataProviderClass = PostDataProvider.class)
  @Tag("Scenario: View posts")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if user can view all comments related to a specific post")
  public void verifyPostHasComments(int id, int expectedStatus) {
    PostApi.getCommentsForPost(id, expectedStatus)
      .then()
      .body("size()", greaterThan(0))
      .body("[0].postId", equalTo(id));
  }

  @Test(dataProvider = "deletePost", dataProviderClass = PostDataProvider.class)
  @Tag("Scenario: Delete post")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify if a post can be deleted")
  public void verifyPostCanBeDeletedSuccessfully(int id, int expectedStatus) {
    PostApi.deletePost(id, expectedStatus);
  }
}
