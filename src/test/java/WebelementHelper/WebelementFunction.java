package WebelementHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by vibhu on 3/7/2018.
 */
public class WebelementFunction extends Base{
    public void listhandling(String listWebelement,String value) {
        List<WebElement> allOptions = driver.findElements(By.xpath(listWebelement));
        for (WebElement we : allOptions) {
            if (we.getText().contains(value)) {
                we.click();
            }
        }
    }
}
