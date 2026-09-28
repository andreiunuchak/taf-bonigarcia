package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class NavigationPage extends BasePage {
    private final String URL = ORIGIN + "/selenium-webdriver-java/navigation1.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byDescription = By.xpath("//p");
    private final By byPrevious = By.xpath("//ul[@class='pagination']/li[1]");
    private final By byNext = By.xpath("//ul[@class='pagination']/li[5]");
    private final By byPages = By.xpath("//ul[@class='pagination']/li");
    private final By byBackToIndex = By.xpath("//a[@href='index.html']");

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open NavigationPage: " + URL)
    public NavigationPage open() {
        driver.get(URL);
        waiters.waitUntilPageLoaded();
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Receive description")
    public String getDescription() {
        return driver.findElement(byDescription).getText();
    }

    @Step("Click Previous button")
    public NavigationPage clickPrevious() {
        driver.findElement(byPrevious).click();
        return this;
    }

    @Step("Click Next button")
    public NavigationPage clickNext() {
        driver.findElement(byNext).click();
        return this;
    }

    @Step("Click navigation button #{number}")
    public NavigationPage selectPage(int number) {
        if (number < 1 || number > 3) {
            throw new IllegalArgumentException("Invalid page number. Number must be between 1 and 3");
        }
        driver.findElements(byPages).get(number).click();
        return this;
    }

    @Step("Click BackToIndex button")
    public HomePage clickBackToIndex() {
        driver.findElement(byBackToIndex).click();
        return new HomePage(driver);
    }

    @Step("Check if Previous button is disabled")
    public boolean isPreviousDisabled() {
        return Objects.requireNonNull(driver.findElement(byPrevious).getAttribute("class")).contains("disabled");
    }

    @Step("Check if Next button is disabled")
    public boolean isNextDisabled() {
        return Objects.requireNonNull(driver.findElement(byNext).getAttribute("class")).contains("disabled");
    }

    @Step("Check if navigation button #{number} is highlighted")
    public boolean isPageHighlighted(int number) {
        return Objects.requireNonNull(driver.findElements(byPages).get(number).getAttribute("class")).contains("active");
    }
}
