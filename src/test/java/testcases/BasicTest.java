package testcases;

/**
 * Created by vibhu on 3/5/2018.
 */


import WebelementHelper.Base;
import WebelementHelper.WebelementFunction;
import helper.ExcelReader;
import helper.PropertyFileReader;
import org.testng.annotations.Test;
import java.util.Properties;


public class BasicTest extends Base {

    String homeFile = System.getProperty("user.dir") + "\\src\\test\\resources\\locatorsfile\\home.properties";
    String valueReadingFile = System.getProperty("user.dir") + "\\src\\test\\resources\\testSheets\\valueReading.xlsx";
    String testResultFile=System.getProperty("user.dir") + "\\src\\test\\resources\\testSheets\\testdata.xlsx";
    Properties propHome,propconfig;

  /*  @Test
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
            webelementFunction.listhandling("listOfActualPrice", excelReader.readExcelData(valueReadingFile,"Sheet1","listOfActualPrice"));

           int rowNumberForSpecificTest = excelReader.getRowNumberForSpecificTest(testResultFile,"Shopping","1");
           int columnNumberForSpecificTest = excelReader.getColumnNumberForSpecificTest(testResultFile,"Shopping");
            excelReader.writeExcelData(testResultFile,"Shopping",rowNumberForSpecificTest,"PAkSS",columnNumberForSpecificTest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(String.format(
                    "Test Case Failed" ));
        }

    }
    */
    @Test
    public void testEasy() throws Exception {
        try {
            ExcelReader excelReader = new ExcelReader();
            propHome = PropertyFileReader.propertyReader(homeFile);
            WebelementFunction webelementFunction = new WebelementFunction();
            String subscriptionPopUp = propHome.getProperty("historyButton");


             webelementFunction.untilVisible(subscriptionPopUp);
           webelementFunction.getElement("historyButton").click();
            System.out.println("Total Cards for History is : "+webelementFunction.getElements("card").size());
            webelementFunction.matchedWhetherAListContainingParticularValue("card","The Maze Runner");

             } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(String.format(
                    "Test Case Failed" ));
        }

    }


}

