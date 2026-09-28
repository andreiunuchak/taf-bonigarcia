package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseOverPage extends BasePage {
    private final String URL = ORIGIN + "/selenium-webdriver-java/mouse-over.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byCompassImage = By.xpath("//img[@src='img/compass.png']");
    private final By byCalendarImage = By.xpath("//img[@src='img/calendar.png']");
    private final By byAwardImage = By.xpath("//img[@src='img/award.png']");
    private final By byLandscapeImage = By.xpath("//img[@src='img/landscape.png']");
    private final By byNoteText = By.xpath("./following::div/p");

    public MouseOverPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open MouseOverPage:" + URL)
    public MouseOverPage open() {
        driver.get(URL);
        waiters.waitUntilPageLoaded();
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Hover Compass image")
    public MouseOverPage hoverCompassImage() {
        new Actions(driver).moveToElement(driver.findElement(byCompassImage)).perform();
        return this;
    }

    @Step("Hover Calendar image")
    public MouseOverPage hoverCalendarImage() {
        new Actions(driver).moveToElement(driver.findElement(byCalendarImage)).perform();
        return this;
    }

    @Step("Hover Award image")
    public MouseOverPage hoverAwardImage() {
        new Actions(driver).moveToElement(driver.findElement(byAwardImage)).perform();
        return this;
    }

    @Step("Hover Landscape image")
    public MouseOverPage hoverLandscapeImage() {
        new Actions(driver).moveToElement(driver.findElement(byLandscapeImage)).perform();
        return this;
    }

    @Step("Check if Compass note is displayed")
    public boolean isCompassNoteDisplayed() {
        return driver.findElement(byCompassImage).findElement(byNoteText).isDisplayed();
    }

    @Step("Check if Calendar note is displayed")
    public boolean isCalendarNoteDisplayed() {
        return driver.findElement(byCalendarImage).findElement(byNoteText).isDisplayed();
    }

    @Step("Check if Award note is displayed")
    public boolean isAwardNoteDisplayed() {
        return driver.findElement(byAwardImage).findElement(byNoteText).isDisplayed();
    }

    @Step("Check if Landscape note is displayed")
    public boolean isLandscapeNoteDisplayed() {
        return driver.findElement(byLandscapeImage).findElement(byNoteText).isDisplayed();
    }
}
