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

public class CreateProductTest extends ProductBaseTest {

  @Test(dataProvider = "create-valid", dataProviderClass = ProductDataProvider.class)
  @Story("As a merchant, I want to be able to add new products to inventory")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify a merchant can create a new product given all required data are provided")
  void verifyAddingNewProduct(Map<String, Object> request, ExpectedResponseDto expected) {
    productsApi.create(request, expected.getStatus())
      .then()
      .body(matchesJsonSchemaInClasspath(expected.getSchema()));
  }

  @Test(dataProvider = "create-invalid", dataProviderClass = ProductDataProvider.class)
  @Story("As a merchant, I want to be able to add new products to inventory")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify a merchant cannot add a new product when {desc}")
  void verifyAddingProductWithInvalidData(String desc, Map<String, Object> request, ExpectedResponseDto expected) {
    productsApi.create(request, expected.getStatus())
      .then()
      .body("$", nullValue());
  }
}
