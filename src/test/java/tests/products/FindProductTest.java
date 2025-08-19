package tests.products;

import com.apis.ProductsApi;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class FindProductTest extends ProductBaseTest {
  private final ProductsApi productsApi = new ProductsApi();

  @Test
  @Story("As a client, I want to be able to a particular details")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify retrieving a product by its id")
  void verifyFindProductById() {
    productsApi.findById(1, 200)
      .then()
      .body(matchesJsonSchemaInClasspath("schemas/products/single.json"));
  }

  @Test
  @Story("As a client, I want to be able view all available products")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify retrieving all products")
  void verifyFindAll() {
    productsApi.findAll(200)
      .then()
      .body(matchesJsonSchemaInClasspath("schemas/products/list.json"));
  }
}
