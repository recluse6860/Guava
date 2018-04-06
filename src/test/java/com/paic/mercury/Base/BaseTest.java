package com.paic.mercury.Base;


import org.apache.log4j.PropertyConfigurator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.codeborne.selenide.Selenide;
import com.paic.mercury.listeners.CaseListener;
import javax.annotation.Resource;


/**
 * Created by shijian on 2017/5/1.
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    static {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }
    
    @BeforeSuite
    public void suiteSetUp() {
    		Selenide.open("about:blank");
    }
    

    @AfterSuite
    public void tearDown() {
        Selenide.close();
    }

    /**
     * 失败用例重试3次依旧失败则截图保存
     */
    private void catchScreenshot(){
        CaseListener caseListener = new CaseListener();
        String failCaseName = caseListener.failCaseName;
        boolean caseResult = caseListener.failResult;
        if(caseResult) {
            try {
            		Selenide.screenshot(failCaseName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            caseListener.failCaseName = null;
            caseListener.failResult = false;
        }
    }
}
