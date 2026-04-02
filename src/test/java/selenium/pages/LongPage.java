package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class LongPage extends AbstractPage {
    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/long-page.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byParagraph = By.xpath("//div[@id='content']/p");

    @Override
    @Step("Open LongPage: " + URL)
    public LongPage open() {
        driver.get(URL);
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
    public LongPage scrollToParagraph(int id) {
        List<WebElement> paragraphs = driver.findElements(byParagraph);
        if (id >= paragraphs.size()) throw new IllegalArgumentException("Incorrect paragraph id");
        new Actions(driver).scrollToElement(paragraphs.get(id)).perform();
        return this;
    }

    @Step("Receive the text of the paragraph #{id}")
    public String getParagraphText(int id) {
        List<WebElement> paragraphs = driver.findElements(byParagraph);
        if (id >= paragraphs.size()) throw new IllegalArgumentException("Incorrect paragraph id");
        return paragraphs.get(id).getText();
    }

    @Step("Check if paragraph #{id} is visible on the screen")
    public boolean isParagraphInViewPort(int id) {
        List<WebElement> paragraphs = driver.findElements(byParagraph);
        if (id >= paragraphs.size()) throw new IllegalArgumentException("Incorrect paragraph id");
        return isVisibleInViewport(paragraphs.get(id));
    }

    private Boolean isVisibleInViewport(WebElement element) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , element);
    }
}
