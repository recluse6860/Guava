package com.paic.mercury.Pageobject;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;

public class SearchResultsPage {
  @FindBy(xpath = "//*[@id='1']/h3/a/em")
  private ElementsCollection results;

  public void checkResultsSize(int expectedSize) {
    results.shouldHave(sizeGreaterThan(expectedSize));
  }
  
  public void checkResults(String... expectedTexts) {
    results.shouldHave(texts(expectedTexts));
  }
  
  public ElementsCollection getResults() {
    return results;
  }
}
