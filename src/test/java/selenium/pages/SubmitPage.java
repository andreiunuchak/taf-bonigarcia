package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import selenium.utils.Waiters;

public class SubmitPage extends AbstractPage {
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byDescription = By.xpath("//p");
    private final Waiters waiters;

    public SubmitPage() {
        super();
        this.waiters = new Waiters();
    }

    @Override
    public AbstractPage open() {
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
