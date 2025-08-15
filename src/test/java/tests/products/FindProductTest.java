package tests.products;

import com.apis.ProductsApi;
import org.testng.annotations.Test;
import tests.BaseTest;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class FindProductTest extends BaseTest {
  private final ProductsApi productsApi = new ProductsApi();

  @Test
  void verifyFindProductById() {
    productsApi.findById(1, 200)
      .then()
      .body(matchesJsonSchemaInClasspath("schemas/products/single.json"));
  }

  @Test
  void verifyFindAll() {
    productsApi.findAll(200)
      .then()
      .body(matchesJsonSchemaInClasspath("schemas/products/list.json"));
  }
}
