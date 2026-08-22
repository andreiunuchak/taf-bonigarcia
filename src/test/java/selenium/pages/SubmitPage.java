package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SubmitPage extends BasePage {
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byDescription = By.xpath("//p");

    public SubmitPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public BasePage open() {
        return null;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        waiters.waitUntilPageLoaded();
        return waiters.waitUntilElementPresent(byTitle).getText();
    }

    @Step("Receive Description")
    public String getDescription() {
        waiters.waitUntilPageLoaded();
        return waiters.waitUntilElementPresent(byDescription).getText();
    }
}
