package examples.pages.google;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleResults {
    @FindBy(id = "resultStats")
    private WebElement searchResultStats;

    public String getSearchResultStats() {
        return searchResultStats.getText();
    }
}
