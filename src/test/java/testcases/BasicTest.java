package testcases;

/**
 * Created by vibhu on 3/5/2018.
 */


import WebelementHelper.Base;
import WebelementHelper.WebelementFunction;
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

public class BasicTest extends Base{


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
            WebelementFunction webelementFunction = new WebelementFunction();
            webelementFunction.listhandling(listOfActualPrice,"271");
        } catch (Exception e) {
            e.printStackTrace();
             
        }

    }




    public void untilVisible(String locatorToVisible){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorToVisible))
        ));
    }
}

