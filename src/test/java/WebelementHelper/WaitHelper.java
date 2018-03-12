package WebelementHelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by vibhu on 3/12/2018.
 */
public class WaitHelper extends Base {

    public static final int WEBELEMENT_DEFAULT_TIMEOUT = 20;

    public static void waitFor(Integer timeout) throws InterruptedException {
        //TODO
        Thread.sleep(timeout);
    }

    public static void waitUntilVisible(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilStalenessOf(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public static void waitUntilElementDisappears(WebElement element) throws Exception {
        boolean isVisible = true;
        int counter = 0;
        while (isVisible) {
            waitFor(100);
            counter = counter + 1;
            isVisible = element.isDisplayed();
            if (counter == WEBELEMENT_DEFAULT_TIMEOUT) {
                isVisible = false;
            }
        }
    }

    public static boolean waitForJStoLoad() {

        WebDriverWait wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}
