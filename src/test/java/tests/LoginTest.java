package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LoginPage;
import com.saucedemo.generic.ExcelDataReader;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup(); 
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testValidLogin() {
        // Read data from Excel
        String url = ExcelDataReader.getURL();
        String username = ExcelDataReader.getUsername();
        String password = ExcelDataReader.getPassword();

        // Launch URL
        driver.get(url);

        // Page Object interaction
        loginPage = new LoginPage(driver);
        loginPage.loginToSauceDemo(username, password);

        // Assertion to confirm login success
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"), "Login failed or didn't redirect properly");

        System.out.println("Logged in successfully. Current URL: " + currentUrl);
    }

    


	@AfterClass
    public void tearDown() {
            driver.quit();
        }
    }
