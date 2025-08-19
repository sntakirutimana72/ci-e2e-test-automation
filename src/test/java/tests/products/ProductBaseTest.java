package tests.products;

import com.apis.ProductsApi;
import io.qameta.allure.Epic;
import tests.BaseTest;

import io.qameta.allure.testng.Tag;

import java.util.HashMap;
import java.util.Map;

@Epic("Ordering System")
@Tag("Feature: Stock Inventory")
public abstract class ProductBaseTest extends BaseTest {
  protected ProductsApi productsApi = new ProductsApi();

  protected Map<String, Object> getPayload(Map<String, Object> request) {
    Map<String, Object> payload = new HashMap<>(request);
    payload.remove("id");
    return payload;
  }
}
