package tests.checkout;

import com.page.cart.CartPage;
import com.page.inventory.InventoryItem;
import com.page.inventory.InventoryPage;

import org.testng.annotations.BeforeMethod;
import tests.BaseTest;
import util.TestUtils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public abstract class CheckoutBaseTest extends BaseTest {

  protected CartPage cart;

  @BeforeMethod
  public void initialize() {
    // Sign in before each test to ensure the inventory page is loaded
    InventoryPage inventory = TestUtils.signIn();
    assertTrue(inventory.isLoaded(), "Inventory page is not loaded");

    // Add two items to the cart to ensure there are items to check out
    inventory.getItems()
      .subList(0, 2)
      .forEach(InventoryItem::addToCart);
    assertEquals(inventory.getCartCountBadge().getCount(), 2);

    // Navigate to the cart page
    cart = inventory.goToCart();
    assertTrue(cart.isLoaded(), "Cart page is not loaded");
  }
}
