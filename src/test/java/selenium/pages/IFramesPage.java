package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class IFramesPage extends BasePage {
    private final String URL = ORIGIN + "/selenium-webdriver-java/iframes.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byIFrame = By.id("my-iframe");
    private final By byParagraph = By.xpath("//p");

    public IFramesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open IFramesPage: " + URL)
    public IFramesPage open() {
        driver.get(URL);
        waiters.waitUntilPageLoaded();
        return this;
    }

    @Override
    @Step("Receive title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Receive amount of paragraphs")
    public int getParagraphsAmount() {
        switchToIFrame();
        int amount = driver.findElements(byParagraph).size();
        driver.switchTo().defaultContent();
        return amount;
    }

    @Step("Scroll to paragraph #{id}")
    public IFramesPage scrollToParagraph(int id) {
        switchToIFrame();
        List<WebElement> paragraphs = driver.findElements(byParagraph);
        if (id >= paragraphs.size()) throw new IllegalArgumentException("Incorrect paragraph id");
        new Actions(driver).scrollToElement(paragraphs.get(id)).perform();
        driver.switchTo().defaultContent();
        return this;
    }

    @Step("Receive the text of the paragraph #{id}")
    public String getParagraphText(int id) {
        switchToIFrame();
        List<WebElement> paragraphs = driver.findElements(byParagraph);
        if (id >= paragraphs.size()) throw new IllegalArgumentException("Incorrect paragraph id");
        String text = paragraphs.get(id).getText();
        driver.switchTo().defaultContent();
        return text;
    }

    @Step("Check if paragraph #{id} is visible on the screen")
    public boolean isParagraphInViewPort(int id) {
        switchToIFrame();
        List<WebElement> paragraphs = driver.findElements(byParagraph);
        if (id >= paragraphs.size()) throw new IllegalArgumentException("Incorrect paragraph id");
        boolean isInViewPort = isVisibleInViewport(paragraphs.get(id));
        driver.switchTo().defaultContent();
        return isInViewPort;
    }

    private void switchToIFrame() {
        driver.switchTo().frame(driver.findElement(byIFrame));
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
