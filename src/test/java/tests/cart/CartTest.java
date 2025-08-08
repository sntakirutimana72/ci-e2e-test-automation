package tests.cart;

import com.page.cart.CartPage;
import com.page.inventory.InventoryItem;
import com.page.inventory.InventoryPage;
import tests.BaseTest;
import util.TestUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {

  private InventoryPage inventory;

  @BeforeMethod
  public void initialize() {
    inventory = TestUtils.signIn();
  }

  private void ensureInvPageIsLoaded(InventoryPage page) {
    assertTrue(page.isLoaded(), "Inventory page is not loaded");
    inventory = page;
  }

  private CartPage ensureCartIsLoaded() {
    CartPage cart = inventory.goToCart();
    assertTrue(cart.isLoaded(), "Cart page is not loaded");
    return cart;
  }

  private InventoryItem addToCart() {
    return inventory.getItems().get(0).addToCart();
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify cart item can be removed")
  public void verifyCartItemCanBeRemoved() {
    ensureInvPageIsLoaded(inventory);

    String addedItemTitle = addToCart().getTitle();
    CartPage cart = ensureCartIsLoaded();

    assertEquals(cart.count(), 1, "Cart should contain one item after adding");
    cart.removeItem(cart.getItems().get(0));
    assertEquals(cart.count(), 0, "Cart should be empty after removing the item");
    ensureInvPageIsLoaded(cart.continueShopping());

    InventoryItem removedItem = inventory.findItemByTitle(addedItemTitle);
    assertTrue(removedItem.hasAddToCartButton(), "Removed item should have 'Add to cart' button");
    assertFalse(removedItem.hasRemoveButton(), "Removed item should not have 'Remove' button");
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @Description("Verify checkout option does not appear when cart is empty")
  public void verifyCheckoutOptionDoesNotAppearWhenCartIsEmpty() {
    ensureInvPageIsLoaded(inventory);
    assertFalse(ensureCartIsLoaded().isCheckoutOptionAvailable());
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify checkout option is available when cart has items")
  public void verifyCheckoutOptionIsAvailableWhenCartHasItems() {
    ensureInvPageIsLoaded(inventory);
    addToCart();
    assertTrue(ensureCartIsLoaded().isCheckoutOptionAvailable());
  }
}
