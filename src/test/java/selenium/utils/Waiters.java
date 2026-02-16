package selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.webdriver.DriverHolder;

import java.time.Duration;
import java.util.Objects;

public class Waiters {
    private final WebDriverWait wait;

    public Waiters() {
        int DEFAULT_WAIT_DURATION_SECONDS = 15;
        this.wait = new WebDriverWait(DriverHolder.getDriver(), Duration.ofSeconds(DEFAULT_WAIT_DURATION_SECONDS));
    }

    public Waiters(int waitDurationSeconds) {
        this.wait = new WebDriverWait(DriverHolder.getDriver(), Duration.ofSeconds(waitDurationSeconds));
    }

    public WebElement waitUntilElementPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public Boolean waitUntilElementRemoved(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
        catch (TimeoutException _) {}
        return wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(locator)));
    }

    public WebElement waitUntilElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitUntilElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilPageLoaded() {
        wait.until(webDriver -> Objects.requireNonNull(((JavascriptExecutor) webDriver).executeScript("return document.readyState")).equals("complete"));
    }

}
