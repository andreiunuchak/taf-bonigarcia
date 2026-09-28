package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GeolocationPage extends BasePage {
    private final String URL = ORIGIN + "/selenium-webdriver-java/geolocation.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byGetGeolocation = By.id("get-coordinates");
    private final By byCoordinates = By.id("coordinates");

    public GeolocationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open Geolocation:" + URL)
    public BasePage open() {
        driver.get(URL);
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Click GetGeolocation button")
    public GeolocationPage clickGetGeolocation() {
        waiters.waitUntilElementClickable(byGetGeolocation).click();
        return this;
    }

    @Step("Click GetGeolocation button")
    public String getCoordinates() {
        return waiters.waitUntilElementVisible(byCoordinates).getText();
    }
}
