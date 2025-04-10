package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.saucedemo.generic.ExcelDataReader;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.*;

public class CheckoutPageTest {
    WebDriver driver;
    LoginPage loginPage;
    AddToCart addToCart;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testCheckoutFlow() throws InterruptedException {
    	driver.get(ExcelDataReader.getURL());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToSauceDemo(ExcelDataReader.getUsername(), ExcelDataReader.getPassword());
        
        addToCart = new AddToCart(driver);
        addToCart.addFirstProductToCart();

        driver.findElement(By.className("shopping_cart_link")).click();

        cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCheckoutInformation("Tanisha", "K", "12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        // Optional, just to ensure elements load
        String message = checkoutPage.getSuccessMessage();
        Assert.assertTrue(message.contains("Thank you"), "Expected success message to contain 'Thank you'");
    }

	@AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
