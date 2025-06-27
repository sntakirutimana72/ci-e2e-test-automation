package tests;

import base.TestFeedAbstract;
import com.page.modal.Subscribed;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestFeed extends TestFeedAbstract {

  @Tag("Validation")
  @Test
  public void verifySubscriptionWithEmptyEmail() {
    feedPage.hitSubscribe();
    assertTrue(feedData.validation().emptyMessage().equalsIgnoreCase(feedPage.error()));
  }

  @Tag("Validation")
  @Test
  public void verifySubscriptionWithInvalidEmail() {
    feedPage.enterEmail(feedData.subscribe().invalidEmail());
    feedPage.hitSubscribe();
    assertTrue(feedData.validation().invalidMessage().equalsIgnoreCase(feedPage.error()));
  }

  @Tag("Functional")
  @Test
  public void verifySuccessfulSubscription() {
    Subscribed subscribed = feedPage.subscribe(feedData.subscribe().validEmail());
    assertTrue(subscribed.isSuccessModalVisible());
  }

  @Tag("Functional")
  @Tag("Accessibility")
  @Test
  public void verifySubscriptionUsingEnter() {
    Subscribed subscribed = feedPage.subscribeUsingEnter(feedData.subscribe().validEmail());
    assertTrue(subscribed.isSuccessModalVisible());
  }

  @Tag("Functional")
  @Tag("Accessibility")
  @Test
  public void verifySubscriptionUsingSpace() {
    Subscribed subscribed = feedPage.subscribeUsingSpace(feedData.subscribe().validEmail());
    assertTrue(subscribed.isSuccessModalVisible());
  }
}
