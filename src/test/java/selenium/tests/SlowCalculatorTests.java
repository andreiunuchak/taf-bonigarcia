package selenium.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.HomePage;
import selenium.pages.SlowCalculatorPage;

import java.util.Random;
import java.util.stream.Stream;

@Headless
@Story(Namespaces.Stories.SLOW_CALCULATOR)
public class SlowCalculatorTests extends BaseTest {

    private SlowCalculatorPage slowCalculatorPage;

    @BeforeEach
    public void openPage() {
        slowCalculatorPage = new HomePage().open().clickSlowCalculator();
    }

    @Test
    @DisplayName("Validate the title on the SlowCalculator page")
    public void testNavigationTitle() {
        Assertions.assertEquals("Slow calculator", slowCalculatorPage.getTitle());
    }

    @ParameterizedTest
    @MethodSource("calculationData")
    @DisplayName("Test calculation operations")
    public void testCalculateOperation(String operation, String expectedResult) {
        Assertions.assertEquals(expectedResult, slowCalculatorPage.calcOperation(operation).waitForCalculationEnd().getResult());
    }

    @ParameterizedTest
    @MethodSource("calculationData")
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
    @DisplayName("Test cleaning result field")
    public void testClearField() {
        slowCalculatorPage.calcOperation("2+2");
        Assertions.assertFalse(slowCalculatorPage.getResult().isEmpty());
        slowCalculatorPage.clearResult();
        Assertions.assertTrue(slowCalculatorPage.getResult().isEmpty());
    }

    public static Stream<Arguments> calculationData() {
        return Stream.of(
                Arguments.of("2+2=", "4"),
                Arguments.of("12*2=", "24"),
                Arguments.of("12/6=", "2"),
                Arguments.of("12-6=", "6"),
                Arguments.of("10/3=", "3.3333333333333335"),
                Arguments.of("1/0=", "Infinity"),
                Arguments.of("100000000000000000000*100000000000000000000=", "1e+40"),
                Arguments.of("1-10=", "-9")
        );
    }
}
