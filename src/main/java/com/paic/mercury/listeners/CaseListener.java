package com.paic.mercury.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Iterator;

/**
 * 用例监听器，监听每一个测试用例的测试结果
 * Created by shijian on 2017/7/23.
 */
public class CaseListener extends TestListenerAdapter{
    private static Logger log = Logger.getLogger(CaseListener.class);
    public static String failCaseName = null;
    public static boolean failResult = false;

    /**
     * 监听用例失败
     */
    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        failResult(tr);
    }

    /**
     * 监听用例通过
     */
    @Override
    public void onTestSuccess(ITestResult tr){
        super.onTestSuccess(tr);
        passResult(tr);
    }

    /**
     * 监听用例skip
     */
    @Override
    public void onTestSkipped(ITestResult tr){
        super.onTestSkipped(tr);
        skipResult(tr);
    }

    /**
     * 监听用例开始
     */
    @Override
    public void onTestStart(ITestResult tr) {
        super.onTestStart(tr);
        log.info("#监听器日志# 开始执行用例: "+tr.getName());
    }

    /**
     * 监听用例结束
     */
    @Override
    public void onFinish(ITestContext context) {
        Iterator<ITestResult> listOfFailedTests = context.getFailedTests().getAllResults().iterator();
        while (listOfFailedTests.hasNext()) {
            ITestResult failedTest = listOfFailedTests.next();
            ITestNGMethod method = failedTest.getMethod();
            if (context.getFailedTests().getResults(method).size() > 1) {
                listOfFailedTests.remove();
            } else {
                if (context.getPassedTests().getResults(method).size() > 0) {
                    listOfFailedTests.remove();
                }
            }
        }
    }

    /**
     * 功能描述:当case skip更新结果 <br>
     * 〈功能详细描述〉
     * @param tr
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private void skipResult(ITestResult tr){
        String skipInfo = "#监听器日志# " + tr.getName() + " 用例skipped. Skip 信息: \n" +tr.getThrowable().toString();
        log.info(skipInfo);
    }

    /**
     * 功能描述:case pass打印相应的log信息 <br>
     * 〈功能详细描述〉
     * @param tr
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private void passResult(ITestResult tr){
        String passInfo = "#监听器日志# " + tr.getName() + " 测试通过";
        log.info(passInfo);
    }

    /**
     * 功能描述: 当case fail更新结果<br>
     * 〈功能详细描述〉
     * @param tr
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private void failResult(ITestResult tr){
        failCaseName = tr.getName();
        failResult = true;
        String errorInfo = "#监听器日志# " + tr.getName() + " 测试失败. 失败信息: \n" +tr.getThrowable().toString();
        log.error(errorInfo);
    }
}
