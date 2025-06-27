package tests;

import base.TestFeedAbstract;
import com.page.modal.Subscribed;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubscribedTest extends TestFeedAbstract {

  private Subscribed assertHasSubscribed() {
    Subscribed subscribed = feedPage.subscribe(feedData.subscribe().validEmail());
    assertTrue(subscribed.isSuccessModalVisible());
    return subscribed;
  }

  @Tag("Accessibility")
  @Test
  public void verifyModalIsDismissible() {
    Subscribed subscribed = assertHasSubscribed();

    subscribed.dismiss();
    assertTrue(subscribed.isSuccessModalAbsent());
  }

  @Tag("Accessibility")
  @Test
  public void verifyModalCanBeDismissedUsingEscape() {
    Subscribed subscribed = assertHasSubscribed();
    subscribed.pressEscape();
    assertTrue(subscribed.isSuccessModalAbsent());
  }

  @Tag("Accessibility")
  @Test
  public void verifyModalCanBeDismissedUsingBackspace() {
    Subscribed subscribed = assertHasSubscribed();
    subscribed.dismissUsingSpace();
    assertTrue(subscribed.isSuccessModalAbsent());
  }

  @Tag("Accessibility")
  @Test
  public void verifyModalCanBeDismissedUsingEnter() {
    Subscribed subscribed = assertHasSubscribed();
    subscribed.dismissUsingEnter();
    assertTrue(subscribed.isSuccessModalAbsent());
  }
}
