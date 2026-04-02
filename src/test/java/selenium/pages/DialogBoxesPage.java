package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import selenium.utils.Waiters;

public class DialogBoxesPage extends AbstractPage {
    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byLaunchAlertButton = By.id("my-alert");
    private final By byLaunchConfirmButton = By.id("my-confirm");
    private final By byLaunchPromptButton = By.id("my-prompt");
    private final By byLaunchModalButton = By.id("my-modal");
    private final By byLaunchConfirmText = By.id("confirm-text");
    private final By byLaunchPromptText = By.id("prompt-text");
    private final By byLaunchModalText = By.id("modal-text");
    private final By byModalDialogBox = By.id("example-modal");
    private final By byModalDialogBoxDismissButton = By.xpath(".//button[@class='btn btn-secondary model-button']");
    private final By byModalDialogBoxConfirmButton = By.xpath(".//button[@class='btn btn-primary model-button']");
    private final By byModalDialogBoxTitle = By.id("exampleModalLabel");
    private final By byModalDialogBoxMessage = By.xpath("//div[@class='modal-body']");

    @Override
    @Step("Open DialogBoxesPage: " + URL)
    public DialogBoxesPage open() {
        driver.get(URL);
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Click Launch Alert button")
    public DialogBoxesPage clickLaunchAlert() {
        driver.findElement(byLaunchAlertButton).click();
        return this;
    }

    @Step("Click Launch Confirm button")
    public DialogBoxesPage clickLaunchConfirm() {
        driver.findElement(byLaunchConfirmButton).click();
        return this;
    }

    @Step("Click Launch Prompt button")
    public DialogBoxesPage clickLaunchPrompt() {
        driver.findElement(byLaunchPromptButton).click();
        return this;
    }

    @Step("Click Launch Modal button")
    public DialogBoxesPage clickLaunchModal() {
        driver.findElement(byLaunchModalButton).click();
        new Waiters().waitUntilElementVisible(byModalDialogBox);
        return this;
    }

    @Step("Click 'Confirm' button on the DialogBox")
    public DialogBoxesPage confirmDialogBox() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException _) {
            WebElement btn = new Waiters().waitUntilElementVisible(byModalDialogBox).findElement(byModalDialogBoxConfirmButton);
            new Waiters().waitUntilElementClickable(btn).click();
            new Waiters().waitUntilElementInvisible(byModalDialogBox);
        }
        return this;
    }

    @Step("Click 'Dismiss' button on the DialogBox")
    public DialogBoxesPage dismissDialogBox() {
        try {
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException _) {
            WebElement btn = new Waiters().waitUntilElementVisible(byModalDialogBox).findElement(byModalDialogBoxDismissButton);
            new Waiters().waitUntilElementClickable(btn).click();
            new Waiters().waitUntilElementInvisible(byModalDialogBox);
        }
        return this;
    }

    @Step("Type '{message}' into the DialogBox")
    public DialogBoxesPage typeIntoDialogBox(String message) {
        driver.switchTo().alert().sendKeys(message);
        return this;
    }

    @Step("Receive Modal DialogBox title")
    public String getModalDialogBoxTitle() {
        return new Waiters().waitUntilElementVisible(byModalDialogBoxTitle).getText();
    }

    @Step("Receive DialogBox message")
    public String getDialogBoxMessage() {
        String message = "";
        try {
            message = driver.switchTo().alert().getText();
        } catch (NoAlertPresentException _) {
            message = driver.findElement(byModalDialogBoxMessage).getText();
        }
        return message;
    }

    @Step("Receive text below the 'Launch Confirm' button")
    public String getLaunchConfirmText() {
        return driver.findElement(byLaunchConfirmText).getText();
    }

    @Step("Receive text below the 'Launch Prompt' button")
    public String getLaunchPromptText() {
        return driver.findElement(byLaunchPromptText).getText();
    }

    @Step("Receive text below the 'Launch Modal' button")
    public String getLaunchModalText() {
        return driver.findElement(byLaunchModalText).getText();
    }

    public boolean isDialogBoxPresent() {
        return isAlertPresent() || isModalPresent();
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private boolean isModalPresent() {
        try {
            new Waiters(3).waitUntilElementVisible(byModalDialogBox);
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}
