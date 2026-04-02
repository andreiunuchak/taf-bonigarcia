package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import selenium.utils.Waiters;


public class LoadingImagesPage extends AbstractPage {
    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byStatus = By.id("text");
    private final By byStatusSpinner = By.xpath("//p[@id='text']/span");
    private final By byCompassImage = By.id("text");
    private final By byCalendarImage = By.id("text");
    private final By byAwardImage = By.id("text");
    private final By byLandscapeImage = By.id("text");

    @Override
    @Step("Open LoadingImagesPage: " + URL)
    public LoadingImagesPage open() {
        driver.get(URL);
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Receive Status")
    public String getStatusText() {
        return driver.findElement(byStatus).getText();
    }

    @Step("Validate if loading spinner is displayed")
    public boolean isStatusSpinnerDisplayed() {
        boolean displayed = false;
        try {
            displayed = driver.findElement(byStatusSpinner).isDisplayed();
        } catch (NoSuchElementException | TimeoutException _) {
        }
        return displayed;
    }

    @Step("Validate if Compass image is displayed")
    public boolean isCompassImageDisplayed() {
        return driver.findElement(byCompassImage).isDisplayed();
    }

    @Step("Validate if Calendar image is displayed")
    public boolean isCalendarImageDisplayed() {
        return driver.findElement(byCalendarImage).isDisplayed();
    }

    @Step("Validate if Award image is displayed")
    public boolean isAwardImageDisplayed() {
        return driver.findElement(byAwardImage).isDisplayed();
    }

    @Step("Validate if Landscape image is displayed")
    public boolean isLandscapeImageDisplayed() {
        return driver.findElement(byLandscapeImage).isDisplayed();
    }

    @Step("Waiting for images loading to complete")
    public LoadingImagesPage waitForLoadingToComplete() {
        new Waiters().waitUntilElementRemoved(byStatusSpinner);
        return this;
    }
}
