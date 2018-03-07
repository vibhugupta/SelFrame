package WebelementHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vibhu on 3/7/2018.
 */
public class Base {

    public static WebDriver driver;
    String chromeDriverFileLocation = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
    @BeforeTest
    public void beforeTest() {


        System.setProperty("webdriver.chrome.driver", chromeDriverFileLocation);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

}
