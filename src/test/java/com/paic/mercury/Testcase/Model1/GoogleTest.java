package com.paic.mercury.Testcase.Model1;

import com.paic.mercury.Base.BaseTest;
import com.paic.mercury.Pageobject.GooglePage;
import com.paic.mercury.Pageobject.SearchResultsPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {
  @Test
  public void userCanSearch()  {
    GooglePage page = open("https://www.baidu.com/", GooglePage.class);
    SearchResultsPage results = page.searchFor("selenide");
    
    results.checkResultsSize(1);
    results.getResults().get(0).shouldHave(text("Selenide: concise UI tests in Java"));
  }
}
