package testcases;

/**
 * Created by vibhu on 3/5/2018.
 */


import helper.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasicTest {
    private WebDriver driver;
    String chromeDriverFileLocation = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
    String configFile = System.getProperty("user.dir") + "\\src\\test\\resources\\locatorsfile\\config.properties";
    String homeFile = System.getProperty("user.dir") + "\\src\\test\\resources\\locatorsfile\\home.properties";
    Properties propConfig;
    Properties propHome;
    @Test
    public void testEasy() {
        try {
            propConfig = PropertyFileReader.propertyReader(configFile);
            propHome = PropertyFileReader.propertyReader(homeFile);
            String listOfActualPrice = propHome.getProperty("listOfActualPrice");
            String subscriptionPopUp = propHome.getProperty("subscriptionPopUp");
            driver.get(propConfig.getProperty("URL"));
            String title = driver.getTitle();
            Assert.assertTrue(title.contains("Online Shopping India | Shop online best offers & daily deals in IN | Awok.com"));
            untilVisible(subscriptionPopUp);
            driver.findElement(By.xpath(propHome.getProperty("subscriptionPopUpClose"))).click();
            driver.findElement(By.id(propHome.getProperty("mainSearchBox"))).sendKeys("Iphone Mobile Charger");
            driver.findElement(By.id(propHome.getProperty("mainSearchButton"))).click();
            listhandling(listOfActualPrice,"271");
        } catch (Exception e) {
            e.printStackTrace();
             e.printStackTrace();
        }

    }

    @BeforeTest
    public void beforeTest() {


        System.setProperty("webdriver.chrome.driver", chromeDriverFileLocation);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterTest
    public void afterTest() {
        //     driver.quit();
    }

    public void listhandling(String listWebelement,String value) {
        List<WebElement> allOptions = driver.findElements(By.xpath(listWebelement));
        for (WebElement we : allOptions) {
            if (we.getText().contains(value)) {
                we.click();
            }
        }
    }

    public void untilVisible(String locatorToVisible){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorToVisible))
        ));
    }
}

