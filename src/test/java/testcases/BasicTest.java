package testcases;

/**
 * Created by vibhu on 3/5/2018.
 */


import WebelementHelper.Base;
import WebelementHelper.WaitHelper;
import WebelementHelper.WebelementFunction;
import helper.ExcelReader;
import helper.PropertyFileReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.json.JsonOutput;
import org.testng.annotations.Test;


import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.codehaus.groovy.tools.shell.util.Logger.io;


public class BasicTest extends Base {

    String homeFile = System.getProperty("user.dir") + "\\src\\test\\resources\\locatorsfile\\home.properties";
    String valueReadingFile = System.getProperty("user.dir") + "\\src\\test\\resources\\testSheets\\valueReading.xlsx";
    String testResultFile = System.getProperty("user.dir") + "\\src\\test\\resources\\testSheets\\testdata.xlsx";
    Properties propHome, propconfig;

      @Test
      public void testEasy() throws Exception {
          try {
              ExcelReader excelReader = new ExcelReader();
              propHome = PropertyFileReader.propertyReader(homeFile);
             // String subscriptionPopUp = propHome.getProperty("subscriptionPopUp");
              String subscriptionPopUp = propHome.getProperty("historyButton");

              WebelementFunction webelementFunction = new WebelementFunction();
               webelementFunction.untilVisible(subscriptionPopUp);
          //   webelementFunction.getElement("subscriptionPopUpClose").click();
             webelementFunction.getElement("historyButton").click();
              webelementFunction.getElement("mainSearchBox").sendKeys(excelReader.readExcelData(valueReadingFile,"Sheet1","mainSearchBox"));
              webelementFunction.getElement("mainSearchButton").click();
              webelementFunction.matchedWhetherAListContainingParticularValue("listOfActualPrice", excelReader.readExcelData(valueReadingFile,"Sheet1","listOfActualPrice"));

             int rowNumberForSpecificTest = excelReader.getRowNumberForSpecificTest(testResultFile,"Shopping","1");
             int columnNumberForSpecificTest = excelReader.getColumnNumberForSpecificTest(testResultFile,"Shopping");
              excelReader.writeExcelData(testResultFile,"Shopping",rowNumberForSpecificTest,"PAkSS",columnNumberForSpecificTest);
          } catch (Exception e) {
              e.printStackTrace();
              throw new Exception(String.format(
                      "Test Case Failed" ));
          }

      }

   // @Test
 /*   public void testEasy() throws Exception {
        ExcelReader excelReader = new ExcelReader();
        int rowNumberForSpecificTest = excelReader.getRowNumberForSpecificTest(testResultFile, "Shopping", "1");
        int columnNumberForSpecificTest = excelReader.getColumnNumberForSpecificTest(testResultFile, "Shopping");
        try {
            propHome = PropertyFileReader.propertyReader(homeFile);
            WebelementFunction webelementFunction = new WebelementFunction();
            String subscriptionPopUp = propHome.getProperty("historyButton");

            webelementFunction.untilVisible(subscriptionPopUp);
            webelementFunction.getElement("historyButton").click();
            System.out.println("Total Cards for History is : " + webelementFunction.getElements("card").size());
            WaitHelper.waitFor(2000);
            webelementFunction.matchedWhetherAListContainingParticularValue("card", "Dunkirk");
            excelReader.writeExcelData(testResultFile, "Shopping", rowNumberForSpecificTest, "PASS", columnNumberForSpecificTest);
         //   webelementFunction.moseHoverAndClick("mouseHoverFirstCard","postMouseHover");
            webelementFunction.moseHoverAndGetText("mouseHoverFirstCard","postMouseHover");




        } catch (Exception e) {

            excelReader.writeExcelData(testResultFile, "Shopping", rowNumberForSpecificTest, "Fail", columnNumberForSpecificTest);
            e.printStackTrace();
            throw new Exception(String.format(
                    "Test Case Failed"));
        }

    }
*/


}

