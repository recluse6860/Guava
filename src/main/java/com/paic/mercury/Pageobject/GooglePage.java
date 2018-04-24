package com.paic.mercury.Pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

import org.openqa.selenium.By;

public class GooglePage {
  private SelenideElement kw =$(By.xpath("//input[@id='kw']"));
  
  
  public SearchResultsPage searchFor(String text) {
	  kw.sendKeys(text);
    //kw.val().pressEnter();
    return page(SearchResultsPage.class);
  }
}
