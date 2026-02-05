package selenium.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import selenium.constants.Namespaces;
import selenium.webdriver.DriverHolder;

import java.io.ByteArrayInputStream;
import java.util.Objects;

@Epic(Namespaces.Epics.UI)
public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = DriverHolder.getDriver();
    }

    @AfterEach
    public void tearDown() {
        Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)), "png");
        Allure.addAttachment("Page source", "text/html", Objects.requireNonNull(driver.getPageSource()));
    }
}
