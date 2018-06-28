package examples.pages.google;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearch {
    @FindBy(name = "q")
    private WebElement searchField;

    public GoogleSearch fillSearchField(String question) {
        searchField.sendKeys(question);
        return this;
    }

    public void search() {
        searchField.submit();
    }
}
