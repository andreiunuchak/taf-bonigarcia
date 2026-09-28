package selenium.pages;

import org.openqa.selenium.WebDriver;
import selenium.utils.Waiters;

public abstract class BasePage {
    public static final String ORIGIN = "https://bonigarcia.dev";
    protected WebDriver driver;
    protected Waiters  waiters;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waiters = new Waiters(driver);
    }

    public abstract BasePage open();

    public abstract String getTitle();
}
