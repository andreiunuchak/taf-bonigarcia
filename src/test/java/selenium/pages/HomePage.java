package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import selenium.utils.Waiters;

public class HomePage extends AbstractPage {
    public final String URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private final By byTitle = By.xpath("//h1");
    private final By bySubtitle = By.xpath("//h1/following-sibling::h5");
    private final By byDescription = By.xpath("//p");
    private final By byChapterThreeTitle = By.xpath("(//div[@class='card-body'])[1]/h5");
    private final By byChapterFourTitle = By.xpath("(//div[@class='card-body'])[2]/h5");
    private final By byChapterFiveTitle = By.xpath("(//div[@class='card-body'])[3]/h5");
    private final By byChapterSevenTitle = By.xpath("(//div[@class='card-body'])[4]/h5");
    private final By byChapterEightTitle = By.xpath("(//div[@class='card-body'])[5]/h5");
    private final By byChapterNineTitle = By.xpath("(//div[@class='card-body'])[6]/h5");
    private final By byWebForm = By.xpath("//a[@href='web-form.html']");
    private final By byNavigation = By.xpath("//a[@href='navigation1.html']");
    private final By byDropDownMenu = By.xpath("//a[@href='dropdown-menu.html']");
    private final By byMouseOver = By.xpath("//a[@href='mouse-over.html']");
    private final By byDragAndDrop = By.xpath("//a[@href='drag-and-drop.html']");
    private final By byDrawInCanvas = By.xpath("//a[@href='draw-in-canvas.html']");
    private final By byLoadingImages = By.xpath("//a[@href='loading-images.html']");
    private final By bySlowCalculator = By.xpath("//a[@href='slow-calculator.html']");
    private final By byLongPage = By.xpath("//a[@href='long-page.html']");
    private final By byInfiniteScroll = By.xpath("//a[@href='infinite-scroll.html']");
    private final By byShadowDom = By.xpath("//a[@href='shadow-dom.html']");
    private final By byCookies = By.xpath("//a[@href='cookies.html']");
    private final By byFrames = By.xpath("//a[@href='frames.html']");
    private final By byIFrames = By.xpath("//a[@href='iframes.html']");
    private final By byDialogBoxes = By.xpath("//a[@href='dialog-boxes.html']");
    private final By byWebStorage = By.xpath("//a[@href='web-storage.html']");
    private final By byGeolocation = By.xpath("//a[@href='geolocation.html']");
    private final By byNotifications = By.xpath("//a[@href='notifications.html']");
    private final By byGetUserMedia = By.xpath("//a[@href='get-user-media.html']");
    private final By byMultilanguage = By.xpath("//a[@href='multilanguage.html']");
    private final By byConsoleLogs = By.xpath("//a[@href='console-logs.html']");
    private final By byLoginForm = By.xpath("//a[@href='login-form.html']");
    private final By bySlowLogin = By.xpath("//a[@href='login-slow.html']");
    private final By byRandomCalculator = By.xpath("//a[@href='random-calculator.html']");
    private final By byDownloadFiles = By.xpath("//a[@href='download.html']");
    private final By byABTesting = By.xpath("//a[@href='ab-testing.html']");
    private final By byDataTypes = By.xpath("//a[@href='data-types.html']");

    public HomePage() {
        super();
    }

    @Override
    @Step("Open HomePage: " + URL)
    public HomePage open() {
        driver.get(URL);
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Receive SubTitle")
    public String getSubTitle() {
        return driver.findElement(bySubtitle).getText();
    }

    @Step("Receive Description")
    public String getDescription() {
        return driver.findElement(byDescription).getText();
    }

    @Step("Click WebForm button")
    public WebFormPage clickWebForm() {
        driver.findElement(byWebForm).click();
        return new WebFormPage();
    }

    public HomePage clickNavigation() {
        driver.findElement(byNavigation).click();
        return new HomePage();
    }

    public HomePage clickDropDownMenu() {
        driver.findElement(byDropDownMenu).click();
        return new HomePage();
    }

    public HomePage clickMouseOver() {
        driver.findElement(byMouseOver).click();
        return new HomePage();
    }

    public HomePage clickDragAndDrop() {
        driver.findElement(byDragAndDrop).click();
        return new HomePage();
    }

    public HomePage clickDrawInCanvas() {
        driver.findElement(byDrawInCanvas).click();
        return new HomePage();
    }

    public HomePage clickLoadingImages() {
        driver.findElement(byLoadingImages).click();
        return new HomePage();
    }

    public HomePage clickSlowCalculator() {
        driver.findElement(bySlowCalculator).click();
        return new HomePage();
    }

    public HomePage clickLongPage() {
        driver.findElement(byLongPage).click();
        return new HomePage();
    }

    public HomePage clickInfiniteScroll() {
        driver.findElement(byInfiniteScroll).click();
        return new HomePage();
    }

    public HomePage clickShadowDom() {
        driver.findElement(byShadowDom).click();
        return new HomePage();
    }

    public HomePage clickCookies() {
        driver.findElement(byCookies).click();
        return new HomePage();
    }

    public HomePage clickFrames() {
        driver.findElement(byFrames).click();
        return new HomePage();
    }

    public HomePage clickIFrames() {
        driver.findElement(byIFrames).click();
        return new HomePage();
    }

    public HomePage clickDialogBoxes() {
        driver.findElement(byDialogBoxes).click();
        return new HomePage();
    }

    public HomePage clickWebStorage() {
        driver.findElement(byWebStorage).click();
        return new HomePage();
    }

    public HomePage clickGeolocation() {
        driver.findElement(byGeolocation).click();
        return new HomePage();
    }

    public HomePage clickNotifications() {
        driver.findElement(byNotifications).click();
        return new HomePage();
    }

    public HomePage clickGetUserMedia() {
        driver.findElement(byGetUserMedia).click();
        return new HomePage();
    }

    public HomePage clickMultilanguage() {
        driver.findElement(byMultilanguage).click();
        return new HomePage();
    }

    public HomePage clickConsoleLogs() {
        driver.findElement(byConsoleLogs).click();
        return new HomePage();
    }

    public HomePage clickLoginForm() {
        driver.findElement(byLoginForm).click();
        return new HomePage();
    }

    public HomePage clickSlowLogin() {
        driver.findElement(bySlowLogin).click();
        return new HomePage();
    }

    public HomePage clickRandomCalculator() {
        driver.findElement(byRandomCalculator).click();
        return new HomePage();
    }

    public HomePage clickDownloadFiles() {
        driver.findElement(byDownloadFiles).click();
        return new HomePage();
    }

    public HomePage clickABTesting() {
        driver.findElement(byABTesting).click();
        return new HomePage();
    }

    public HomePage clickDataTypes() {
        driver.findElement(byDataTypes).click();
        return new HomePage();
    }

    public String getChapterThreeTitle() {
        return driver.findElement(byChapterThreeTitle).getText();
    }

    public String getChapterFourTitle() {
        return driver.findElement(byChapterFourTitle).getText();
    }

    public String getChapterFiveTitle() {
        return driver.findElement(byChapterFiveTitle).getText();
    }

    public String getChapterSevenTitle() {
        return driver.findElement(byChapterSevenTitle).getText();
    }

    public String getChapterEightTitle() {
        return driver.findElement(byChapterEightTitle).getText();
    }

    public String getChapterNineTitle() {
        return driver.findElement(byChapterNineTitle).getText();
    }

}
