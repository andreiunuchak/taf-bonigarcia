package selenium.tests;

import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import selenium.annotations.TestFailureExtension;
import selenium.constants.Namespaces;
import selenium.webdriver.DriverHolder;

@Epic(Namespaces.Epics.UI)
@ExtendWith(TestFailureExtension.class)
public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = DriverHolder.getDriver();
    }
}
