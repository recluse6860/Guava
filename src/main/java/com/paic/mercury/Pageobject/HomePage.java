package com.paic.mercury.Pageobject;

import com.codeborne.selenide.Selenide;

public class HomePage {
	
	private final String JoinUsLink = "//div[@class='footert']//a[contains(@href,'help-30')]";
	
	public void EnterJoinUs(String Url,String data1) {
		
		Selenide.open(Url);
		Selenide.sleep(1000);
		Selenide.$x(JoinUsLink).click();
	}

}
