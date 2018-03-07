package WebelementHelper;

import helper.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by vibhu on 3/7/2018.
 */
public class WebelementFunction extends Base{

    String homeFile = System.getProperty("user.dir") + "\\src\\test\\resources\\locatorsfile\\home.properties";
    Properties propHome;

    public void listhandling(String listWebelement,String value) throws Exception {
        List<WebElement> allOptions = getElements(listWebelement);
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

    public WebElement getElement(String element) throws Exception {
        WebElement elem = null;
       String elementType = element.concat("Type");
        propHome= PropertyFileReader.propertyReader(homeFile);
        try{
            if ((propHome.getProperty(elementType)).equalsIgnoreCase("xpath")){
                elem=driver.findElement(By.xpath(propHome.getProperty(element)));
            }else  if ((propHome.getProperty(elementType)).equalsIgnoreCase("id")){
                elem=driver.findElement(By.id(propHome.getProperty(element)));
            } else if ((propHome.getProperty(elementType)).equalsIgnoreCase("css")) {
                elem = driver.findElement(By.cssSelector(propHome.getProperty(element)));
            } else if ((propHome.getProperty(elementType)).equalsIgnoreCase("class")) {
                elem = driver.findElement(By.className(propHome.getProperty(element)));
            } else if ((propHome.getProperty(elementType)).equalsIgnoreCase("partialLink")) {
                elem = driver.findElement(By
                        .partialLinkText(propHome.getProperty(element)));
            } else if ((propHome.getProperty(elementType)).equalsIgnoreCase("name")) {
                elem = driver.findElement(By.name(propHome.getProperty(element)));
            } else if ((propHome.getProperty(elementType)).equalsIgnoreCase("tagname")) {
                elem = driver.findElement(By.tagName(propHome.getProperty(element)));
            } else {
                throw new Exception(String.format(
                        "Element is not supported at the moment",
                        (propHome.getProperty(element))));
            }
        } catch(Exception e){
            throw new Exception(String.format(
                    "Element is not supported at the moment",
                    (propHome.getProperty(element))));
        }

        return elem;
    }

    public List<WebElement> getElements(String element) throws Exception {

        List<WebElement> elementList = new ArrayList<WebElement>();
        try {
            elementList = getWebElements(element);
            if (elementList.size() == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(String.format(
                    "Element %s is not found in the page", element));
        }
        return elementList;
    }

    public List<WebElement> getWebElements(String locator)
            throws Exception {
        String elementType = locator.concat("Type");
        List<WebElement> elementList = new ArrayList<WebElement>();
        propHome= PropertyFileReader.propertyReader(homeFile);

                if ((propHome.getProperty(elementType)).equalsIgnoreCase("id")) {
                    elementList = driver.findElements(By.id(propHome.getProperty(locator)));
                } else if ((propHome.getProperty(elementType)).equalsIgnoreCase("css")) {
                    elementList = driver.findElements(By.cssSelector(propHome.getProperty(locator)));
                } else if ((propHome.getProperty(elementType)).equalsIgnoreCase("class")) {
                    elementList = driver.findElements(By.className(propHome.getProperty(locator)));
                } else if ((propHome.getProperty(elementType)).equalsIgnoreCase("partialLink")) {
                    elementList = driver.findElements(By
                            .partialLinkText(propHome.getProperty(locator)));
                } else if ((propHome.getProperty(elementType)).equalsIgnoreCase("xpath")) {
                    elementList = driver.findElements(By.xpath(propHome.getProperty(locator)));
                } else if ((propHome.getProperty(elementType)).equalsIgnoreCase("name")) {
                    elementList = driver.findElements(By.name(propHome.getProperty(locator)));
                } else if ((propHome.getProperty(elementType)).equalsIgnoreCase("tagname")) {
                    elementList = driver.findElements(By.tagName(propHome.getProperty(locator)));
                } else {
                    throw new Exception(String.format(
                            "Element type %s is not supported at the moment",
                            (propHome.getProperty(elementType))));
                }

        return elementList;

    }
}
