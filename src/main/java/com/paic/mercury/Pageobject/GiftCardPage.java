package com.paic.mercury.Pageobject;

import com.codeborne.selenide.Selenide;

public class GiftCardPage {
	
	private final String EnterLipince = "//div[@class='gift_home_bg01']//a[contains(@href,'lipince')]";

	public void EnterLipince(String Url,String data2) {
		Selenide.open(Url);
		Selenide.sleep(1000);
		Selenide.$x(EnterLipince).click();
	}
		
}
