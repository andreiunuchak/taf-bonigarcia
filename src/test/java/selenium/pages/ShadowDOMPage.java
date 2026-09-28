package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShadowDOMPage extends BasePage {
    private final String URL = ORIGIN + "/selenium-webdriver-java/long-page.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byShadowDOM = By.id("content");
    private final By byText = By.cssSelector("p");

    public ShadowDOMPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open ShadowDOMPage: " + URL)
    public ShadowDOMPage open() {
        driver.get(URL);
        waiters.waitUntilPageLoaded();
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Receive Text")
    public String getText() {
        return driver.findElement(byShadowDOM).getShadowRoot().findElement(byText).getText();
    }
}
