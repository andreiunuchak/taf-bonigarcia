package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import selenium.utils.Waiters;

public class DropdownMenuPage extends AbstractPage {
    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byLeftClickDropdown = By.id("my-dropdown-1");
    private final By byRightClickDropdown = By.id("my-dropdown-2");
    private final By byDoubleClickDropdown = By.id("my-dropdown-3");
    private final By byLeftClickDropdown_Action = By.xpath("(//ul[@class='dropdown-menu show']/li/a)[1]");
    private final By byLeftClickDropdown_AnotherAction = By.xpath("(//ul[@class='dropdown-menu show']/li/a)[2]");
    private final By byLeftClickDropdown_SomethingElseHere = By.xpath("(//ul[@class='dropdown-menu show']/li/a)[3]");
    private final By byLeftClickDropdown_SeparatedLink = By.xpath("(//ul[@class='dropdown-menu show']/li/a)[4]");
    private final By byRightClickDropdown_Action = By.xpath("(//ul[@id='context-menu-2']/li/a)[1]");
    private final By byRightClickDropdown_AnotherAction = By.xpath("(//ul[@id='context-menu-2']/li/a)[2]");
    private final By byRightClickDropdown_SomethingElseHere = By.xpath("(//ul[@id='context-menu-2']/li/a)[3]");
    private final By byRightClickDropdown_SeparatedLink = By.xpath("(//ul[@id='context-menu-2']/li/a)[4]");
    private final By byDoubleClickDropdown_Action = By.xpath("(//ul[@id='context-menu-3']/li/a)[1]");
    private final By byDoubleClickDropdown_AnotherAction = By.xpath("(//ul[@id='context-menu-3']/li/a)[2]");
    private final By byDoubleClickDropdown_SomethingElseHere = By.xpath("(//ul[@id='context-menu-3']/li/a)[3]");
    private final By byDoubleClickDropdown_SeparatedLink = By.xpath("(//ul[@id='context-menu-3']/li/a)[4]");

    @Override
    @Step("Open DropdownMenuPage: " + URL)
    public DropdownMenuPage open() {
        driver.get(URL);
        return this;
    }

    @Override
    @Step("Receive title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Click Left-Click dropdown button")
    public DropdownMenuPage clickLeftClickDropdown() {
        new Actions(driver).moveToElement(driver.findElement(byLeftClickDropdown)).click().perform();
        return this;
    }

    @Step("Click Right-Click dropdown button")
    public DropdownMenuPage clickRightClickDropdown() {
        new Actions(driver).moveToElement(driver.findElement(byRightClickDropdown)).contextClick().perform();
        return this;
    }

    @Step("Click Double-Click dropdown button")
    public DropdownMenuPage clickDoubleClickDropdown() {
        new Actions(driver).moveToElement(driver.findElement(byDoubleClickDropdown)).doubleClick().perform();
        return this;
    }

    @Step("Click Action button on the Left-Click dropdown")
    public DropdownMenuPage clickLeftClickDropdown_Action() {
        new Waiters().waitUntilElementClickable(byLeftClickDropdown_Action);
        new Actions(driver).moveToElement(driver.findElement(byLeftClickDropdown_Action)).click().perform();
        return this;
    }

    @Step("Click Action button on the Right-Click dropdown")
    public DropdownMenuPage clickRightClickDropdown_Action() {
        new Actions(driver).moveToElement(driver.findElement(byRightClickDropdown_Action)).click().perform();
        return this;
    }

    @Step("Click Action button on the Double-Click dropdown")
    public DropdownMenuPage clickDoubleClickDropdown_Action() {
        new Actions(driver).moveToElement(driver.findElement(byDoubleClickDropdown_Action)).click().perform();
        return this;
    }

    @Step("Click AnotherAction button on the Left-Click dropdown")
    public DropdownMenuPage clickLeftClickDropdown_AnotherAction() {
        new Actions(driver).moveToElement(driver.findElement(byLeftClickDropdown_AnotherAction)).click().perform();
        return this;
    }

    @Step("Click AnotherAction button on the Right-Click dropdown")
    public DropdownMenuPage clickRightClickDropdown_AnotherAction() {
        new Actions(driver).moveToElement(driver.findElement(byRightClickDropdown_AnotherAction)).click().perform();
        return this;
    }

    @Step("Click AnotherAction button on the Double-Click dropdown")
    public DropdownMenuPage clickDoubleClickDropdown_AnotherAction() {
        new Actions(driver).moveToElement(driver.findElement(byDoubleClickDropdown_AnotherAction)).click().perform();
        return this;
    }

    @Step("Click SomethingElseHere button on the Left-Click dropdown")
    public DropdownMenuPage clickLeftClickDropdown_SomethingElseHere() {
        new Actions(driver).moveToElement(driver.findElement(byLeftClickDropdown_SomethingElseHere)).click().perform();
        return this;
    }

    @Step("Click SomethingElseHere button on the Right-Click dropdown")
    public DropdownMenuPage clickRightClickDropdown_SomethingElseHere() {
        new Actions(driver).moveToElement(driver.findElement(byRightClickDropdown_SomethingElseHere)).click().perform();
        return this;
    }

    @Step("Click SomethingElseHere button on the Double-Click dropdown")
    public DropdownMenuPage clickDoubleClickDropdown_SomethingElseHere() {
        new Actions(driver).moveToElement(driver.findElement(byDoubleClickDropdown_SomethingElseHere)).click().perform();
        return this;
    }

    @Step("Click SeparateLink button on the Left-Click dropdown")
    public DropdownMenuPage clickLeftClickDropdown_SeparatedLink() {
        new Actions(driver).moveToElement(driver.findElement(byLeftClickDropdown_SeparatedLink)).click().perform();
        return this;
    }

    @Step("Click SeparateLink button on the Right-Click dropdown")
    public DropdownMenuPage clickRightClickDropdown_SeparatedLink() {
        new Actions(driver).moveToElement(driver.findElement(byRightClickDropdown_SeparatedLink)).click().perform();
        return this;
    }

    @Step("Click SeparateLink button on the Double-Click dropdown")
    public DropdownMenuPage clickDoubleClickDropdown_SeparatedLink() {
        new Actions(driver).moveToElement(driver.findElement(byDoubleClickDropdown_SeparatedLink)).click().perform();
        return this;
    }
}
