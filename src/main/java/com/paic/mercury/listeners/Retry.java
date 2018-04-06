package com.paic.mercury.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Created by shijian on 2017/11/20.
 */
public class Retry implements IRetryAnalyzer {

    private int retryCount = 0;
    private int maxRetryCount = 3;   // retry a failed test 2 additional times

    public boolean retry(ITestResult result) {
        if (retryCount <maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
