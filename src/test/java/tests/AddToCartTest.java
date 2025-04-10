package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.saucedemo.generic.ExcelDataReader;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AddToCart;
import pages.LoginPage;

public class AddToCartTest {
    WebDriver driver;
    AddToCart addToCart;

    @BeforeMethod
    public void login() {
        WebDriverManager.chromedriver().setup(); 
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Step 1: Open URL and login
        driver.get(ExcelDataReader.getURL());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToSauceDemo(ExcelDataReader.getUsername(), ExcelDataReader.getPassword());

        addToCart = new AddToCart(driver);   
    }

    @Test
    public void testAddToCart() {
        // Step 2: Add item to cart
        addToCart.addFirstProductToCart();

        // Step 3: Go to cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // Step 4: Verify item is in cart
        String cartItemName = driver.findElement(By.className("inventory_item_name")).getText();

        // Optional: Wait briefly
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 5: Assertion
        Assert.assertNotNull(cartItemName, "Cart should contain at least one item");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}