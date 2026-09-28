package selenium.annotations;

import io.qameta.allure.Allure;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import selenium.webdriver.DriverHolder;

import java.io.ByteArrayInputStream;
import java.util.Objects;

public class TestFailureExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(@NonNull ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            WebDriver driver = DriverHolder.getDriver();
            if (driver != null) {
                try {
                    Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)), "png");
                    Allure.addAttachment("Page source", "text/html", Objects.requireNonNull(driver.getPageSource()), "html");
                } catch (Exception e) {
                    System.err.println("Failed to attach failure artifacts: " + e.getMessage());
                }
            }
        }
    }
}
