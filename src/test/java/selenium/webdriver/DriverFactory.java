package selenium.webdriver;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

public abstract class DriverFactory {

    private static final String DEFAULT_PROXY = System.getProperty("proxy.url", "localhost:8080");

    public static WebDriver getDriver(boolean proxy, boolean geolocation, String... options) {
        String remoteURL = Optional.ofNullable(System.getenv("remote_url")).orElse(System.getProperty("remote_url"));
        String browserType = System.getProperty("browser", "chrome").toLowerCase();

        AbstractDriverOptions<?> driverOptions = createOptions(browserType, proxy, geolocation, remoteURL != null && !remoteURL.isBlank(), options);

        WebDriver driver = (remoteURL != null && !remoteURL.isBlank())
                ? createRemoteDriver(remoteURL, driverOptions)
                : createLocalDriver(driverOptions);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        return driver;
    }

    private static AbstractDriverOptions<?> createOptions(String browserType, boolean proxy, boolean geolocation, boolean isRemote, String... options) {
        AbstractDriverOptions<?> driverOptions = switch (browserType) {
            case "chrome" -> buildChromeOptions(isRemote, geolocation, options);
            case "firefox" -> buildFirefoxOptions(isRemote, geolocation, options);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserType);
        };

        if (proxy) {
            Proxy proxyP = new Proxy()
                    .setHttpProxy(DEFAULT_PROXY)
                    .setSslProxy(DEFAULT_PROXY);
            driverOptions.setProxy(proxyP);
            driverOptions.setAcceptInsecureCerts(true);
        }
        return driverOptions;
    }

    private static ChromeOptions buildChromeOptions(boolean isRemote, boolean geolocation, String... options) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(options);
        if (isRemote) {
            chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            chromeOptions.setCapability("goog:loggingPrefs", Map.of("browser", "ALL"));
        } else {
            chromeOptions.addArguments("--disable-search-engine-choice-screen");
        }
        if (geolocation) {
            chromeOptions.enableBiDi();
        }
        return chromeOptions;
    }

    private static FirefoxOptions buildFirefoxOptions(boolean isRemote, boolean geolocation, String... options) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments(options);
        if (isRemote) {
            firefoxOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            firefoxOptions.setCapability("moz:debuggerAddress", true);
        }
        if (geolocation) {
            firefoxOptions.enableBiDi();
        }
        return firefoxOptions;
    }

    private static WebDriver createRemoteDriver(String remoteURL, AbstractDriverOptions<?> options) {
        try {
            return new RemoteWebDriver(URI.create(remoteURL).toURL(), options);
        } catch (MalformedURLException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid Remote WebDriver URL: " + remoteURL, e);
        }
    }

    private static WebDriver createLocalDriver(AbstractDriverOptions<?> options) {
        if (options instanceof ChromeOptions chromeOptions) {
            return new ChromeDriver(chromeOptions);
        } else if (options instanceof FirefoxOptions firefoxOptions) {
            return new FirefoxDriver(firefoxOptions);
        }
        throw new IllegalArgumentException("Unsupported options type: " + options.getClass().getName());
    }
}
