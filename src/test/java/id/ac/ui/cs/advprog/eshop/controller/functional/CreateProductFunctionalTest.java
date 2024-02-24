package id.ac.ui.cs.advprog.eshop.controller.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;
    private String createProductUrl;
    private String productListUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
        createProductUrl = baseUrl + "/product/create";
        productListUrl = baseUrl + "/product/list";
    }

    @Test
    void createProduct_isCorrect(ChromeDriver driver) throws Exception {

        // Create Product on createProduct Page
        driver.get(createProductUrl);

        driver.findElement(By.id("nameInput")).sendKeys("Sampo Cap Bambang");
        driver.findElement(By.id("quantityInput")).sendKeys("100");
        driver.findElement(By.tagName("form")).submit();

        // Check if the Product exist in the productList Page
        driver.get(productListUrl);

        String productName = driver.findElement(By.id("productName")).getText();
        String productQuantity = driver.findElement(By.id("productQuantity")).getText();

        assertEquals(productName, "Sampo Cap Bambang");
        assertEquals(productQuantity,"100");
    }
}