package selenium.webdriver;

import org.openqa.selenium.WebDriver;

import java.util.Optional;

public final class DriverHolder {
    private static final ThreadLocal<WebDriver> THREAD_LOCAL_WEBDRIVER = new ThreadLocal<>();

    private DriverHolder() {
    }

    public static void setDriver(WebDriver driver) {
        if (THREAD_LOCAL_WEBDRIVER.get() != null) {
            throw new IllegalStateException("Driver is already initialized in this thread. Call removeDriver() first.");
        }
        THREAD_LOCAL_WEBDRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        return THREAD_LOCAL_WEBDRIVER.get();
    }

    public static void removeDriver() {
        Optional.ofNullable(THREAD_LOCAL_WEBDRIVER.get()).ifPresent(driver -> {
            try {
                driver.quit();
            } finally {
                THREAD_LOCAL_WEBDRIVER.remove();
            }
        });
    }
}
