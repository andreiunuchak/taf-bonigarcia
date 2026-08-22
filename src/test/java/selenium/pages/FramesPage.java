package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class FramesPage extends BasePage {
    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/frames.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byHeaderFrame = By.xpath("//frame[@name='frame-header']");
    private final By byBodyFrame = By.xpath("//frame[@name='frame-body']");
    private final By byFooterFrame = By.xpath("//frame[@name='frame-footer']");
    private final By byParagraph = By.xpath("//p");

    public FramesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open FramesPage: " + URL)
    public FramesPage open() {
        driver.get(URL);
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        driver.switchTo().frame(driver.findElement(byHeaderFrame));
        String title = driver.findElement(byTitle).getText();
        driver.switchTo().defaultContent();
        return title;
    }

    @Step("Receive paragraphs texts")
    public List<String> getParagraphsTexts() {
        driver.switchTo().frame(driver.findElement(byBodyFrame));
        List<String> paragraphsTexts = driver.findElements(byParagraph).stream().map(WebElement::getText).toList();
        driver.switchTo().defaultContent();
        return paragraphsTexts;
    }

    @Step("Receive body frame height")
    public int getBodyFrameHeight() {
        return driver.findElement(byBodyFrame).getSize().getHeight();
    }

    @Step("Resize top border of the body frame")
    public FramesPage resizeTopBorder(int value) {
        WebElement bodyFrame = driver.findElement(byBodyFrame);
        new Actions(driver).moveToLocation(bodyFrame.getSize().getWidth() / 2, bodyFrame.getLocation().getY() - 1).clickAndHold().moveByOffset(0, value).release().perform();
        return this;
    }

    @Step("Resize bottom border of the body frame")
    public FramesPage resizeBottomBorder(int value) {
        WebElement bodyFrame = driver.findElement(byBodyFrame);
        new Actions(driver).moveToLocation(bodyFrame.getSize().getWidth() / 2, bodyFrame.getLocation().getY() + bodyFrame.getSize().getHeight() + 1).clickAndHold().moveByOffset(0, value).release().perform();
        return this;
    }


}
