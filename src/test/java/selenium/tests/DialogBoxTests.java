package selenium.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.DialogBoxesPage;
import selenium.pages.HomePage;

@Headless
@Story(Namespaces.Stories.DIALOG_BOXES)
public class DialogBoxTests extends BaseTest {
    private DialogBoxesPage dialogBoxPage;

    @BeforeEach
    public void openPage() {
        dialogBoxPage = new HomePage(driver).open().clickDialogBoxes();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate the title on the DialogBox page")
    public void testTitle() {
        Assertions.assertEquals("Dialog boxes", dialogBoxPage.getTitle());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate Alert dialog box")
    public void testAlertDialogBox() {
        dialogBoxPage.clickLaunchAlert();
        Assertions.assertTrue(dialogBoxPage.isDialogBoxPresent());
        dialogBoxPage.confirmDialogBox();
        Assertions.assertFalse(dialogBoxPage.isDialogBoxPresent());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate Confirm dialog box pressing 'Confirm'")
    public void testConfirmDialogBoxConfirmation() {
        dialogBoxPage.clickLaunchConfirm();
        Assertions.assertTrue(dialogBoxPage.isDialogBoxPresent());
        dialogBoxPage.confirmDialogBox();
        Assertions.assertAll(
                () -> Assertions.assertFalse(dialogBoxPage.isDialogBoxPresent()),
                () -> Assertions.assertEquals("You chose: true", dialogBoxPage.getLaunchConfirmText())
        );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Tag(Namespaces.Severity.MEDIUM)
    @DisplayName("Validate Confirm dialog box pressing 'Dismiss'")
    public void testConfirmDialogBoxDismission() {
        dialogBoxPage.clickLaunchConfirm();
        Assertions.assertTrue(dialogBoxPage.isDialogBoxPresent());
        dialogBoxPage.dismissDialogBox();
        Assertions.assertAll(
                () -> Assertions.assertFalse(dialogBoxPage.isDialogBoxPresent()),
                () -> Assertions.assertEquals("You chose: false", dialogBoxPage.getLaunchConfirmText())
        );
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate Prompt dialog box pressing 'Confirm'")
    public void testPromptDialogBoxConfirmation() {
        String prompt = new Faker().name().fullName();
        dialogBoxPage.clickLaunchPrompt();
        Assertions.assertTrue(dialogBoxPage.isDialogBoxPresent());
        dialogBoxPage.typeIntoDialogBox(prompt).confirmDialogBox();
        Assertions.assertAll(
                () -> Assertions.assertFalse(dialogBoxPage.isDialogBoxPresent()),
                () -> Assertions.assertEquals("You typed: " + prompt, dialogBoxPage.getLaunchPromptText())
        );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Tag(Namespaces.Severity.MEDIUM)
    @DisplayName("Validate Prompt dialog box pressing 'Dismiss'")
    public void testPromptDialogBoxDismission() {
        String prompt = new Faker().name().fullName();
        dialogBoxPage.clickLaunchPrompt();
        Assertions.assertTrue(dialogBoxPage.isDialogBoxPresent());
        dialogBoxPage.typeIntoDialogBox(prompt).dismissDialogBox();
        Assertions.assertAll(
                () -> Assertions.assertFalse(dialogBoxPage.isDialogBoxPresent()),
                () -> Assertions.assertEquals("You typed: null", dialogBoxPage.getLaunchPromptText())
        );
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate Modal dialog box pressing 'Confirm'")
    public void testModalDialogBoxConfirmation() {
        dialogBoxPage.clickLaunchModal();
        Assertions.assertAll(
                () -> Assertions.assertTrue(dialogBoxPage.isDialogBoxPresent()),
                () -> Assertions.assertEquals("Modal title", dialogBoxPage.getModalDialogBoxTitle()),
                () -> Assertions.assertEquals("This is the modal body", dialogBoxPage.getDialogBoxMessage())
        );
        dialogBoxPage.confirmDialogBox();
        Assertions.assertAll(
                () -> Assertions.assertFalse(dialogBoxPage.isDialogBoxPresent()),
                () -> Assertions.assertEquals("You chose: Save changes", dialogBoxPage.getLaunchModalText())
        );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Tag(Namespaces.Severity.MEDIUM)
    @DisplayName("Validate Modal dialog box pressing 'Dismiss'")
    public void testModalDialogBoxDismission() {
        dialogBoxPage.clickLaunchModal();
        Assertions.assertAll(
                () -> Assertions.assertTrue(dialogBoxPage.isDialogBoxPresent()),
                () -> Assertions.assertEquals("Modal title", dialogBoxPage.getModalDialogBoxTitle()),
                () -> Assertions.assertEquals("This is the modal body", dialogBoxPage.getDialogBoxMessage())
        );
        dialogBoxPage.dismissDialogBox();
        Assertions.assertAll(
                () -> Assertions.assertFalse(dialogBoxPage.isDialogBoxPresent()),
                () -> Assertions.assertEquals("You chose: Close", dialogBoxPage.getLaunchModalText())
        );
    }
}
