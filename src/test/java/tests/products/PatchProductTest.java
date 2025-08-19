package tests.products;

import com.dto.ExpectedResponseDto;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import providers.ProductDataProvider;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.nullValue;

public class PatchProductTest extends ProductBaseTest {

  @Test(dataProvider = "patch-valid", dataProviderClass = ProductDataProvider.class)
  @Story("As a merchant, I want to be able to update existing products")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify a merchant can update existing products provided valid data")
  void verifyProductUpdateGivenValidData(Map<String, Object> request, ExpectedResponseDto expected) {
    String productId = (String) request.get("id");
    productsApi.update(productId, getPayload(request), expected.getStatus())
      .then()
      .body(matchesJsonSchemaInClasspath(expected.getSchema()));
  }

  @Test(dataProvider = "patch-invalid", dataProviderClass = ProductDataProvider.class)
  @Story("As a merchant, I want to be able to add new products to inventory")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify a merchant cannot update a product when {desc}")
  void verifyAddingProductWithInvalidData(String desc, Map<String, Object> request, ExpectedResponseDto expected) {
    String productId = (String) request.get("id");
    productsApi.update(productId, getPayload(request), expected.getStatus())
      .then()
      .body("$", nullValue());
  }
}
