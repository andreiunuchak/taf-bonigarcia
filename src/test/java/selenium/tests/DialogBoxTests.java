package selenium.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
        dialogBoxPage = new HomePage().open().clickDialogBoxes();
    }

    @Test
    @DisplayName("Validate the title on the DialogBox page")
    public void testTitle() {
        Assertions.assertEquals("Dialog boxes", dialogBoxPage.getTitle());
    }

    @Test
    @DisplayName("Validate Alert dialog box")
    public void testAlertDialogBox() {
        dialogBoxPage.clickLaunchAlert();
        Assertions.assertTrue(dialogBoxPage.isDialogBoxPresent());
        dialogBoxPage.confirmDialogBox();
        Assertions.assertFalse(dialogBoxPage.isDialogBoxPresent());
    }

    @Test
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
