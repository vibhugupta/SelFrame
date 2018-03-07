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

public class BasicTest extends Base {

    String homeFile = System.getProperty("user.dir") + "\\src\\test\\resources\\locatorsfile\\home.properties";
    Properties propHome;

    @Test
    public void testEasy() throws Exception {
        try {

            propHome = PropertyFileReader.propertyReader(homeFile);
            String listOfActualPrice = propHome.getProperty("listOfActualPrice");
            String subscriptionPopUp = propHome.getProperty("subscriptionPopUp");
            WebelementFunction webelementFunction = new WebelementFunction();
            webelementFunction.untilVisible(subscriptionPopUp);
            webelementFunction.getElement("subscriptionPopUpClose").click();
            webelementFunction.getElement("mainSearchBox").sendKeys("Iphone Mobile Charger");
            webelementFunction.getElement("mainSearchButton").click();
            webelementFunction.listhandling("listOfActualPrice", "271");
        } catch (Exception e) {
            throw new Exception(String.format(
                    "Test Case Failed" ));
        }

    }


}

