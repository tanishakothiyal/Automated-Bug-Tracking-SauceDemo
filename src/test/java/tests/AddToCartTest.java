package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AddToCart;
import pages.LoginPage;

public class AddToCartTest {
	WebDriver driver;
	AddToCart AddToCart;

@BeforeMethod
public void login() {
	 WebDriverManager.chromedriver().setup(); 
     driver = new ChromeDriver();
     driver.manage().window().maximize();
     driver.get("https://www.saucedemo.com/");
     AddToCart = new AddToCart(driver);     		
}
@Test
public void testAddToCart() {
    // Step 1: Login
    LoginPage loginPage = new LoginPage(driver);
    loginPage.enterUsername("standard_user");
    loginPage.enterPassword("secret_sauce");
    loginPage.clickLogin();
    
	//Step 2: Add item to cart
AddToCart addToCart = new AddToCart(driver);
addToCart.addFirstProductToCart();

// Step 3: Go to cart
driver.findElement(By.className("shopping_cart_link")).click();

// Step 4: Verify item is in cart

String cartItemName = driver.findElement(By.className("inventory_item_name")).getText();
//Wait briefly to ensure the item appears in the cart
try {
    Thread.sleep(2000); // 2 seconds pause
} catch (InterruptedException e) {
    e.printStackTrace(); // Optional: handle or log it
}
Assert.assertNotNull(cartItemName, "Cart should contain at least one item");
}
}