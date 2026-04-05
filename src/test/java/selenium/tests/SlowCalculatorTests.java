package selenium.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.HomePage;
import selenium.pages.SlowCalculatorPage;

import java.util.Random;

@Headless
@Story(Namespaces.Stories.SLOW_CALCULATOR)
public class SlowCalculatorTests extends BaseTest {

    private SlowCalculatorPage slowCalculatorPage;

    @BeforeEach
    public void openPage() {
        slowCalculatorPage = new HomePage().open().clickSlowCalculator();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate the title on the SlowCalculator page")
    public void testNavigationTitle() {
        Assertions.assertEquals("Slow calculator", slowCalculatorPage.getTitle());
    }

    @ParameterizedTest
    @MethodSource("selenium.utils.TestDataProvider#mathCalculationsTestData")
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Test calculation operations")
    public void testCalculateOperation(String operation, String expectedResult) {
        Assertions.assertEquals(expectedResult, slowCalculatorPage.calcOperation(operation).waitForCalculationEnd().getResult());
    }

    @ParameterizedTest
    @MethodSource("selenium.utils.TestDataProvider#mathCalculationsTestData")
    @Severity(SeverityLevel.NORMAL)
    @Tag(Namespaces.Severity.MEDIUM)
    @DisplayName("Test calculation operations with various delays")
    public void testCalculateOperationWithDelays(String operation, String expectedResult) {
        int delay = new Random().nextInt(1, 15);
        slowCalculatorPage.setDelay(delay);
        Assertions.assertAll(
                () -> Assertions.assertEquals(delay, slowCalculatorPage.getDelay()),
                () -> Assertions.assertEquals(expectedResult, slowCalculatorPage.calcOperation(operation).waitForCalculationEnd().getResult())
        );
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Test cleaning result field")
    public void testClearField() {
        slowCalculatorPage.calcOperation("2+2");
        Assertions.assertFalse(slowCalculatorPage.getResult().isEmpty());
        slowCalculatorPage.clearResult();
        Assertions.assertTrue(slowCalculatorPage.getResult().isEmpty());
    }
}
