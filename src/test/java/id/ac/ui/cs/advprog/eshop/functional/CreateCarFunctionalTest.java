package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateCarFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createCar_isCorrect(ChromeDriver driver) throws Exception {

        driver.get(baseUrl + "/car/list");

        driver.findElement(By.linkText("Create Car")).click();

        WebElement nameInput = driver.findElement(By.name("carName"));
        nameInput.clear();
        nameInput.sendKeys("Kijang Innova Zenix");

        WebElement colorInput = driver.findElement(By.name("carColor"));
        colorInput.clear();
        colorInput.sendKeys("Putih Mutiara");

        WebElement quantityInput = driver.findElement(By.name("carQuantity"));
        quantityInput.clear();
        quantityInput.sendKeys("50");

        driver.findElement(By.tagName("button")).click();

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Kijang Innova Zenix"), "Car name should be displayed in the list");
        assertTrue(pageSource.contains("Putih Mutiara"), "Car color should be displayed in the list");
        assertTrue(pageSource.contains("50"), "Car quantity should be displayed in the list");
    }
}