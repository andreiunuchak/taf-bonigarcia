package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import selenium.utils.Waiters;

import java.time.Duration;

public class SlowCalculatorPage extends BasePage {
    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byDelay = By.id("delay");
    private final By byKeys = By.xpath("//div[@class='keys']");
    private final By byResult = By.xpath("//div[@class='screen']");
    private final By byClear = By.xpath("//span[@class='clear btn btn-outline-danger']");
    private final By bySpinner = By.id("spinner']");

    public SlowCalculatorPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open SlowCalculatorPage: " + URL)
    public SlowCalculatorPage open() {
        driver.get(URL);
        waiters.waitUntilPageLoaded();
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Set '{seconds}' delay")
    public SlowCalculatorPage setDelay(int seconds) {
        driver.findElement(byDelay).clear();
        driver.findElement(byDelay).sendKeys(String.valueOf(seconds));
        return this;
    }

    @Step("Receive Delay")
    public int getDelay() {
        return Integer.parseInt(driver.findElement(byDelay).getShadowRoot().findElement(By.cssSelector("div")).getText());
    }

    @Step("Type '{operation}' operation")
    public SlowCalculatorPage calcOperation(String operation) {
        waitForCalcToLoad();
        String[] sequence = operation.replaceAll("/", "÷").replaceAll("\\*", "x").split("");
        Actions actions = new Actions(driver);
        WebElement keyboard = driver.findElement(byKeys);
        WebElement element;
        for (String s : sequence) {
            element = keyboard.findElement(By.xpath(String.format("//*[text()='%s']", s)));
            actions.moveToElement(element).pause(Duration.ofMillis(100)).click().perform();
        }
        return this;
    }

    @Step("Clear result field")
    public SlowCalculatorPage clearResult() {
        driver.findElement(byClear).click();
        return this;
    }

    @Step("Receive calculation result")
    public String getResult() {
        return driver.findElement(byResult).getText();
    }

    @Step("Waiting for calculations")
    public SlowCalculatorPage waitForCalculationEnd() {
        new Waiters(getDelay()).waitUntilElementRemoved(bySpinner);
        return this;
    }

    private void waitForCalcToLoad() {
        String[] characters = "0123456789÷x-+=".split("");
        for (String character : characters) {
            waiters.waitUntilElementClickable(driver.findElement(byKeys).findElement(By.xpath(String.format("//*[text()='%s']", character))));
        }
    }
}
