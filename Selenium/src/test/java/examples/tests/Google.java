package examples.tests;

import examples.pages.google.GoogleResults;
import examples.pages.google.GoogleSearch;
import examples.webdriver.WebDriverFactory;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class Google {
    private static final String PAGE_URL = "https://www.google.be";
    private static final String SEARCH_CONTINUUM_CONSULTING = "Continuum Consulting";

    @Test
    public void googleSearchGoesToResultPage() {
        final String EXPECTED_SEARCH_URL = "https://www.google.be/search?";

        WebDriver webDriver = WebDriverFactory.initDriver();
        webDriver.get(PAGE_URL);

        PageFactory
                .initElements(webDriver, GoogleSearch.class)
                .fillSearchField(SEARCH_CONTINUUM_CONSULTING)
                .search();

        final String currentUrl = webDriver.getCurrentUrl();

        assertThat(currentUrl).startsWith(EXPECTED_SEARCH_URL);
        webDriver.quit();
    }

    @Test
    public void googleGivesStatisticsForSearch() {
        final CharSequence[] EXPECTED_SUBSEQUENCE = {"Ongeveer", "resultaten", "(", "seconden)"};

        WebDriver webDriver = WebDriverFactory.initDriver();
        webDriver.get(PAGE_URL);

        PageFactory
                .initElements(webDriver, GoogleSearch.class)
                .fillSearchField(SEARCH_CONTINUUM_CONSULTING)
                .search();

        String searchResultStats = PageFactory
                .initElements(webDriver, GoogleResults.class)
                .getSearchResultStats();

        assertThat(searchResultStats).containsSubsequence(EXPECTED_SUBSEQUENCE);
        webDriver.quit();
    }
}
