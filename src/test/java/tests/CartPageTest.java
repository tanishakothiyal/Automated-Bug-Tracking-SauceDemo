package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.saucedemo.generic.ExcelDataReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.*;

public class CartPageTest {
    WebDriver driver;
    LoginPage loginPage;
    AddToCart addToCart;
    CartPage cartPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testCartItemDisplayed() throws InterruptedException {
        // Login
    	driver.get(ExcelDataReader.getURL());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToSauceDemo(ExcelDataReader.getUsername(), ExcelDataReader.getPassword());
    
        // Add item to cart
        addToCart = new AddToCart(driver);
        addToCart.addFirstProductToCart();

        // Navigate to cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // Verify cart page
        cartPage = new CartPage(driver);
        String productName = cartPage.getCartItemName();

        Thread.sleep(1000); // Optional delay for element rendering
        Assert.assertNotNull(productName);
        Assert.assertFalse(productName.isEmpty());
    }

	@AfterMethod
    public void tearDown() {
        driver.quit();
    }
}