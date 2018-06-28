package examples.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.time.Duration;

public class DownloadUtils {
    private static final int TIME_OUT = 5;
    private static final int POLLING_INTERVAL = 100;

    public static boolean exists(WebDriver webDriver, String downloadFolder, String fileName) {
        Wait<WebDriver> wait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(TIME_OUT))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL));

        return wait.until(driver -> new File(getPathname(downloadFolder, fileName)).exists());
    }

    public static void remove(String downloadFolder, String file) throws Exception {
        File demo = new File(getPathname(downloadFolder, file));
        if (demo.exists() && !demo.delete()) {
            throw new Exception("We were unable to remove the file.");
        }
    }

    private static String getPathname(String downloadFolder, String fileName) {
        return Settings.DOWNLOAD_FOLDER_LOCATION + downloadFolder + File.separator + fileName;
    }
}

