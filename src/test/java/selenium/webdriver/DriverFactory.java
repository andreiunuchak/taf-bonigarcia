package selenium.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

public abstract class DriverFactory {

    public static WebDriver getDriver(String... options) {
        String remoteURL = Optional.ofNullable(System.getenv("remote_url")).orElse(System.getProperty("remote_url"));
        String browserType = System.getProperty("browser", "chrome").toLowerCase();
        AbstractDriverOptions<?> driverOptions = createOptions(browserType, options);
        WebDriver driver;
        if (remoteURL != null && !remoteURL.isBlank()) {
            driver = createRemoteDriver(remoteURL, driverOptions);
        } else {
            driver = createLocalDriver(driverOptions);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }

    private static AbstractDriverOptions<?> createOptions(String browserType, String... options) {
        AbstractDriverOptions<?> driverOptions = browserType.equals("firefox") ? new FirefoxOptions() : new ChromeOptions();
        for (String option : options) {
            if (driverOptions instanceof ChromeOptions) ((ChromeOptions) driverOptions).addArguments(option);
            if (driverOptions instanceof FirefoxOptions) ((FirefoxOptions) driverOptions).addArguments(option);
        }
        return driverOptions;
    }

    private static WebDriver createRemoteDriver(String remoteURL, AbstractDriverOptions<?> options) {
        if (options instanceof ChromeOptions opt) {
            opt.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            opt.setCapability("goog:loggingPrefs", Map.of("browser", "ALL"));
        }
        if (options instanceof FirefoxOptions opt) {
            opt.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            opt.setCapability("moz:debuggerAddress", true);
        }
        try {
            return new RemoteWebDriver(new URL(remoteURL), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Remote WebDriver URL: " + remoteURL, e);
        }
    }

    private static WebDriver createLocalDriver(AbstractDriverOptions<?> options) {
        if (options instanceof ChromeOptions opt) {
            opt.addArguments("--disable-search-engine-choice-screen");
            return new ChromeDriver(opt);
        }
        return new FirefoxDriver((FirefoxOptions) options);
    }
}
