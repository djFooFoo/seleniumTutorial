package examples.tests;

import examples.pages.springio.SpringDownload;
import examples.webdriver.DownloadUtils;
import examples.webdriver.WebDriverFactory;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringIo {
    private static final String EXPECTED_FILE_NAME = "expectedFileName.zip";
    private static final String DEPENDENCY_JPA = "JPA";
    private static final String GROUP_CONTINUUM = "com.continuum";
    private static final String ARTIFACT = "expectedFileName";
    private static final String PAGE_URL = "https://start.spring.io/";

    @Test
    public void springBootShortDownload() throws Exception {
        final String DOWNLOAD_FOLDER = "shortDownloadFolder";
        WebDriver webDriver = WebDriverFactory.initDriver(DOWNLOAD_FOLDER);
        webDriver.get(PAGE_URL);

        PageFactory
                .initElements(webDriver, SpringDownload.class)
                .withGroup(GROUP_CONTINUUM)
                .addDependency(DEPENDENCY_JPA)
                .withArtifact(ARTIFACT)
                .generateProject();

        assertThat(DownloadUtils.exists(webDriver, DOWNLOAD_FOLDER, EXPECTED_FILE_NAME)).isTrue();

        webDriver.quit();
        DownloadUtils.remove(DOWNLOAD_FOLDER, EXPECTED_FILE_NAME);
    }

    @Test
    public void springBootLongDownload() throws Exception {
        final String DOWNLOAD_FOLDER_SECOND = "longDownloadFolder";
        WebDriver webDriver = WebDriverFactory.initDriver(DOWNLOAD_FOLDER_SECOND);
        webDriver.get(PAGE_URL);

        PageFactory
                .initElements(webDriver, SpringDownload.class)
                .withGroup(GROUP_CONTINUUM)
                .withArtifact(ARTIFACT)
                .withFullVersion()
                .withAllOptions()
                .generateProject();

        assertThat(DownloadUtils.exists(webDriver, DOWNLOAD_FOLDER_SECOND, EXPECTED_FILE_NAME)).isTrue();

        webDriver.quit();
        DownloadUtils.remove(DOWNLOAD_FOLDER_SECOND, EXPECTED_FILE_NAME);
    }
}
