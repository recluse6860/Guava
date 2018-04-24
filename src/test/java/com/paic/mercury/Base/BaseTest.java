package com.paic.mercury.Base;


import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Element;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import com.codeborne.selenide.Selenide;
import com.paic.mercury.listeners.CaseListener;
import com.paic.mercury.utils.ParseXml;
import com.paic.mercury.utils.Global;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by shijian on 2017/5/1.
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    static {
        PropertyConfigurator.configure("src/main/resources/properties/log4j.properties");
    }
    
	private ParseXml px;
	private Map<String, String> commonMap;    
    
    @BeforeClass
    public void suiteSetUp() {
    		//Selenide.open("127.0.0.1");
    }
    

    @AfterClass
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
    
	private void initialPx(){
		if(px==null){			
			px = new ParseXml("src/test/resources/test-data/"+this.getClass().getSimpleName()+".xml");
		}
	}
	
	private void getCommonMap(){
		if(commonMap==null){			
			Element element = px.getElementObject("/*/common");
			commonMap = px.getChildrenInfoByElement(element);
		}
	}
	
	private Map<String, String> getMergeMapData(Map<String, String> map1, Map<String, String> map2){
		Iterator<String> it = map2.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			String value = map2.get(key);
			if(!map1.containsKey(key)){
				map1.put(key, value);
			}
		}
		return map1;
	}
	
	@DataProvider
    public Object[][] providerMethod(Method method){	
		this.initialPx();
		this.getCommonMap();
		String methodName = method.getName();
		List<Element> elements = px.getElementObjects("/*/"+methodName);
		Object[][] object = new Object[elements.size()][];
		for (int i =0; i<elements.size(); i++) {
			Map<String, String> mergeCommon = this.getMergeMapData(px.getChildrenInfoByElement(elements.get(i)), commonMap);
			Map<String, String> mergeGlobal = this.getMergeMapData(mergeCommon, Global.global);
			Object[] temp = new Object[]{mergeGlobal};
			object[i] = temp;
		}
		return object;
	}    
}
