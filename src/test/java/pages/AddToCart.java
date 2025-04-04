package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCart {
	
WebDriver driver;

//Constructor
public AddToCart(WebDriver driver) {
	this.driver=driver;
}
		
private By firstProductAddButton = By.cssSelector(".inventory_item button");

public void addFirstProductToCart() {
    driver.findElement(firstProductAddButton).click();
    
}
}
