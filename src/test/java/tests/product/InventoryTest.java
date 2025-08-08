package tests.product;

import com.page.cart.CartCountBadge;
import com.page.cart.CartItem;
import com.page.cart.CartPage;
import com.page.inventory.InventoryItem;
import com.page.inventory.InventoryPage;

import util.TestUtils;
import tests.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.util.FormatUtil.interpolate;
import static com.util.FormatUtil.numberToWord;
import static org.testng.Assert.*;

public class InventoryTest extends BaseTest {

  private InventoryPage inventoryPage;

  @BeforeMethod
  public void initialize() {
    inventoryPage = TestUtils.signIn();
  }

  void assertCartCountBadge(CartCountBadge badge, InventoryItem item, int expectedCount) {
    executeStep(interpolate("Add {} item to cart", numberToWord(expectedCount)), item::addToCart);
    assertTrue(badge.isDisplayed(), "Cart count badge should be displayed");
    assertEquals(badge.getCount(), expectedCount, interpolate("Cart count should be {}", expectedCount));
  }

  CartPage ensureCartPageIsLoaded() {
    CartPage cart = inventoryPage.goToCart();
    assertTrue(cart.isLoaded(), "Cart page is not loaded");
    return cart;
  }

  void ensureInventoryPageIsLoaded(InventoryPage page) {
    assertTrue(page.isLoaded(), "Inventory page is not loaded");
    inventoryPage = page;
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify products are populated and contain details")
  void verifyProductsArePopulated() {
    ensureInventoryPageIsLoaded(inventoryPage);
    inventoryPage.getProductElements().forEach(item -> {
      assertTrue(item.$("img.inventory_item_img").isDisplayed(), "Product image is not displayed");
      assertTrue(item.$(".inventory_item_name").isDisplayed(), "Product title is not displayed");
      assertTrue(item.$(".inventory_item_desc").isDisplayed(), "Product description is not displayed");
      assertTrue(item.$(".inventory_item_price").isDisplayed(), "Product price is not displayed");
    });
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @Description("Verify cart count badge is updated when adding items")
  void verifyCartCountBadgeWhenAddingToCart() {
    ensureInventoryPageIsLoaded(inventoryPage);

    List<InventoryItem> items = inventoryPage.getItems();
    CartCountBadge cartCountBadge = inventoryPage.getCartCountBadge();

    // At this stage, cart badge should not be displayed
    assertFalse(cartCountBadge.isDisplayed(), "Cart count badge should not be displayed initially");
    // Assert the cart count badge to have only one item
    assertCartCountBadge(cartCountBadge, items.get(0), 1);
    assertCartCountBadge(cartCountBadge, items.get(1), 2);
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @Description("Verify cart count badge is updated when removing items")
  void verifyCartCountBadgeWhenRemovingFromCart() {
    ensureInventoryPageIsLoaded(inventoryPage);

    List<InventoryItem> items = inventoryPage.getItems();
    CartCountBadge cartCountBadge = inventoryPage.getCartCountBadge();

    executeStep("Add two items to cart", () -> {
      items.get(0).addToCart();
      items.get(1).addToCart();
    });

    assertTrue(items.get(0).hasRemoveButton(), "First item should have a remove button");
    assertTrue(items.get(1).hasRemoveButton(), "Second item should have a remove button");
    // Also, cart should be displayed with count 2
    assertTrue(cartCountBadge.isDisplayed(), "Cart count badge should be displayed after adding items");
    assertEquals(cartCountBadge.getCount(), 2, "Cart count should be 2 after adding items");

    executeStep("Remove first item from cart", items.get(0)::removeFromCart);

    assertEquals(cartCountBadge.getCount(), 1, "Cart count should be 1 after removing first item");
    assertFalse(items.get(0).hasRemoveButton(), "First item should not have a remove button after removal");
    assertTrue(items.get(0).hasAddToCartButton(), "First item should have an add to cart button after removal");
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify items added to cart reflect in the cart page")
  void verifyItemsAddedToCartReflectInCartPage() {
    ensureInventoryPageIsLoaded(inventoryPage);

    InventoryItem addedItem = inventoryPage.getItems().get(0).addToCart();
    String addedItemTitle = addedItem.getTitle();
    String addedItemPrice = addedItem.getPrice();
    CartPage cart = ensureCartPageIsLoaded();
    CartItem cartAddedItem = cart.getItems().get(0);

    assertEquals(cart.count(), 1, "Cart should contain one item after adding");
    assertEquals(cartAddedItem.getTitle(), addedItemTitle, "Cart item title should match the added item title");
    assertEquals(cartAddedItem.getPrice(), addedItemPrice, "Cart item price should match the added item price");
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify item `Remove` option from inventory page works correctly")
  void verifyRemoveItemFromInventory() {
    ensureInventoryPageIsLoaded(inventoryPage);

    String addedItemTitle = inventoryPage.getItems().get(0).addToCart().getTitle();
    CartPage cart = ensureCartPageIsLoaded();

    assertEquals(cart.count(), 1, "Cart should contain an item");
    ensureInventoryPageIsLoaded(cart.continueShopping());

    InventoryItem itemToRemove = inventoryPage.findItemByTitle(addedItemTitle);
    assertTrue(itemToRemove.hasRemoveButton(), "Item should have a remove button");
    itemToRemove.removeFromCart();

    cart = ensureCartPageIsLoaded();
    assertEquals(cart.count(), 0, "Cart should be empty after removing item from inventory");
  }
}
