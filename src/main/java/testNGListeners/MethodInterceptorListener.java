package testNGListeners;

import java.util.ArrayList;
import java.util.List;

import helper.ExcelReader;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.log4testng.Logger;

/**
 * Created by vibhu on 3/8/2018.
 */
public class MethodInterceptorListener implements IMethodInterceptor {
    //returns list of methods to be executed by testNG
    final static Logger logger = Logger.getLogger(MethodInterceptorListener.class);

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        ExcelReader excelReader = new ExcelReader();
        List<IMethodInstance> result = new ArrayList();
        try {
            for (IMethodInstance m : methods) {
                String testName = m.getMethod().getConstructorOrMethod().getName();
                if (excelReader.isTestPresent(testName)) {
                    logger.info("running method " + testName);
                    result.add(m);
                } else {
                    logger.info(testName + " is ignored");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
