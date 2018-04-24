package com.paic.mercury.Testcase.Model1;

import java.util.Map;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.paic.mercury.utils.Common;
import com.paic.mercury.Base.BaseTest;
import com.paic.mercury.Pageobject.HomePage;

public class TestHomePage extends BaseTest  {
	
	
	@Resource
	HomePage homePage;
	
	@Test(dataProvider = "providerMethod")
	public void JoinUs (Map<String,String> param) {
		homePage.EnterJoinUs(Common.this.MercuryAdmin, param.get(key));
	}
	

}
