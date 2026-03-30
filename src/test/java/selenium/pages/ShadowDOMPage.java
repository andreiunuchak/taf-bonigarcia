package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ShadowDOMPage extends AbstractPage{
    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/long-page.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byShadowDOM = By.id("content");
    private final By byText = By.cssSelector("p");

    @Override
    @Step("Open ShadowDOMPage: " + URL)
    public ShadowDOMPage open() {
        driver.get(URL);
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
