package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCart {
	
WebDriver driver;
public AddToCart(WebDriver driver) {
	this.driver=driver;
}
		
public void addFirstProductToCart() {
    driver.findElement(By.cssSelector(".inventory_item button")).click();

}
}
