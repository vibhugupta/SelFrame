package WebelementHelper;

import helper.PropertyFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by vibhu on 3/7/2018.
 */
public class Base {

    public static WebDriver driver;
    String chromeDriverFileLocation = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
    String configFile = System.getProperty("user.dir") + "\\src\\test\\resources\\locatorsfile\\config.properties";
    Properties propConfig;

    @BeforeTest
    public void beforeTest() {
        propConfig = PropertyFileReader.propertyReader(configFile);
        System.setProperty("webdriver.chrome.driver", chromeDriverFileLocation);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(propConfig.getProperty("URL"));
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

}
