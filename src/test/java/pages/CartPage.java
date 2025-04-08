package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private By cartItemName = By.className("inventory_item_name");

    public String getCartItemName() {
        return driver.findElement(cartItemName).getText();
    }

    public void clickCheckout() {
        driver.findElement(By.id("checkout")).click();
    }
}