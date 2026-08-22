package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class InfiniteScrollPage extends BasePage {
    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byParagraph = By.xpath("//div[@class='row']//p");

    public InfiniteScrollPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open InfiniteScrollPage: " + URL)
    public InfiniteScrollPage open() {
        driver.get(URL);
        waiters.waitUntilPageLoaded();
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Receive amount of paragraphs")
    public int getParagraphsAmount() {
        return driver.findElements(byParagraph).size();
    }

    @Step("Scroll to paragraph #{id}")
    public InfiniteScrollPage scrollToParagraph(int id) {
        List<WebElement> paragraphs = driver.findElements(byParagraph);
        if (id < 0 || id >= paragraphs.size()) throw new IllegalArgumentException("Incorrect paragraph id");
        new Actions(driver).scrollToElement(paragraphs.get(id)).perform();
        return this;
    }

    @Step("Receive the text of the paragraph #{id}")
    public String getParagraphText(int id) {
        List<WebElement> paragraphs = driver.findElements(byParagraph);
        if (id < 0 || id >= paragraphs.size()) throw new IllegalArgumentException("Incorrect paragraph id");
        return paragraphs.get(id).getText();
    }
}
