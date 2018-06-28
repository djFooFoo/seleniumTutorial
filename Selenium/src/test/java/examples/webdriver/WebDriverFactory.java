package examples.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;

public class WebDriverFactory {
    public static WebDriver initDriver() {
        return initDriver("");
    }

    public static WebDriver initDriver(String folder) {
        return getChromeDriver(folder);
    }

    private static WebDriver getGeckoDriver(String folder) {
        System.setProperty("webdriver.gecko.driver", "C:/drivers/geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "C:/tmp/logs.txt");

        FirefoxProfile firefoxProfile = new FirefoxProfile();

        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.dir", Settings.DOWNLOAD_FOLDER_LOCATION + folder);
        firefoxProfile.setPreference("browser.download.useDownloadDir", true);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.openFile", "application/zip;application/octet-stream");
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip;application/octet-stream");
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.setHeadless(true);

        return new FirefoxDriver(firefoxOptions);
    }

    private static WebDriver getChromeDriver(String folder) {
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", Settings.DOWNLOAD_FOLDER_LOCATION + folder);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("safebrowsing.enabled", true);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(false);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--start-maximized");
        return new ChromeDriver(chromeOptions);
    }
}
