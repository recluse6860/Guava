package com.paic.mercury.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 解析 *.properties 配置文件
 * Created by shijian on 2017/11/20.
 */
@Service
@Slf4j
public class Common {
    public String browser;
    public String MercuryAdmin;
	private String MercuryHomePageUrl;

    /**
     * 设置需要启动的浏览器
     * @param value
     * @return
     */
    @Value("${browser}")
    private void setBrowser(String value) {
        this.browser = value.trim();
        specifyBrowser(browser);
    }

    /**
     * specify browser
     * @param browser
     */
    private void specifyBrowser(String browser){
        String rootPath = System.getProperty("user.dir");
        if(browser.equals("chrome")) {
            Configuration.browser = browser;
            Configuration.startMaximized=true;
            System.setProperty("webdriver."+browser+".driver", rootPath+"/src/main/resources/drivers/chromedriver");
        }else{
            log.info("No match browser: "+browser);
        }
        Configuration.screenshots=false;
    }
    
    @Value("${mercury.homepage}")
    private void setMercuryHomePageUrl(String value) {
        this.MercuryHomePageUrl = value.trim();
    } 
    
}

