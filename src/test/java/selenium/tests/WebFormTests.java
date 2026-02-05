package selenium.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.ElementNotInteractableException;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.HomePage;
import selenium.pages.SubmitPage;
import selenium.pages.WebFormPage;

import java.net.URISyntaxException;
import java.nio.file.Paths;

@Headless
@Story(Namespaces.Stories.WEBFORM)
public class WebFormTests extends BaseTest {
    private WebFormPage webForm;

    @BeforeEach
    public void openWebFormPage() {
        webForm = new HomePage().open().clickWebForm();
    }

    @Test
    @DisplayName("Validate Title on the WebFrom page")
    public void testWebFormTitle() {
        String expectedTitle = "Web form";
        String actualTitle = webForm.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Validate TextInput field behavior on the WebFrom page")
    public void testWebFormTextInput() {
        String expectedText = "Web form";
        webForm.inputTextInput(expectedText);
        String actualText = webForm.getTextInputValue();
        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Validate Password field behavior on the WebFrom page")
    public void testWebFormPassword() {
        String expectedText = "p4$$w0rd";
        webForm.inputPassword(expectedText);
        String actualText = webForm.getPasswordValue();
        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Validate TextArea field behavior on the WebFrom page")
    public void testWebFormTextArea() {
        String expectedText = """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed ultricies elit, tincidunt vestibulum sem. In hac habitasse platea dictumst. Nam eleifend dui diam, eget rutrum nulla sagittis quis. Suspendisse malesuada nisi pulvinar ligula varius, sed euismod dui luctus. Curabitur dictum hendrerit est, ut elementum erat. Quisque erat nisi, dignissim id efficitur ut, faucibus ut ipsum. Nunc at semper orci. Donec sed iaculis est, vitae pellentesque magna. Nam eget velit ac nisl ullamcorper sodales id eu mi. Donec rutrum justo odio, at laoreet dui molestie vel. Phasellus tincidunt sapien nibh, vel tincidunt dui blandit sed. Aenean a enim sapien. Aliquam mi diam, faucibus vel lacinia ut, vehicula ut elit.
                Sed non interdum turpis, a ornare ipsum. Sed porttitor dictum consectetur. Morbi bibendum bibendum lectus fringilla fermentum. Morbi laoreet molestie volutpat. Nulla ut semper lectus, hendrerit lobortis neque. Proin ac velit ut neque posuere sodales eget in nunc. Fusce at nibh ut urna tincidunt rhoncus. Donec tristique lectus vitae consectetur convallis. Pellentesque rhoncus ipsum vitae posuere volutpat.
                Morbi posuere tempor magna sit amet semper. Phasellus faucibus leo eu odio volutpat auctor ac sit amet dui. Fusce quis ligula sed massa lobortis scelerisque ut nec turpis. Nulla sit amet viverra urna, vel molestie arcu. Cras sit amet lacinia ligula. Maecenas lacinia, leo ac tempor viverra, nunc sapien dictum arcu, eget consequat arcu massa quis erat. Mauris id tempus orci, eu tempus nisi. Donec sodales augue eros, sed blandit nibh eleifend eu. Aliquam non purus quis neque iaculis euismod. In convallis sollicitudin felis, non semper elit pharetra eu.
                Donec sollicitudin lacus quis felis cursus, non tempus ante pulvinar. Pellentesque sed libero semper, maximus metus non, pretium lectus. Praesent aliquet dignissim lacus, quis dictum nibh mollis sed. Pellentesque risus neque, dignissim eu imperdiet et, condimentum id ligula. Proin at cursus arcu, id rhoncus orci. Quisque tincidunt, nisi at dapibus ultrices, erat sem imperdiet arcu, vitae semper tellus eros eget purus. Aliquam sed ultricies turpis. Ut lobortis, sapien in lobortis euismod, enim augue laoreet sapien, vitae iaculis mi augue non lacus. Nunc congue, ipsum vitae bibendum sagittis, sem neque suscipit quam, nec euismod erat ipsum ut risus. Aliquam sagittis elit sed ligula facilisis luctus. Vestibulum a urna porta, cursus tellus eget, tincidunt tortor. Mauris porta ipsum orci, nec pulvinar est dictum in. Donec posuere odio vitae libero congue, quis vestibulum velit varius. Duis sed suscipit dui.
                Suspendisse sit amet arcu id justo egestas fringilla eget et mauris. Aenean est nibh, congue in auctor quis, cursus quis quam. In ultricies euismod mollis. Donec non ante malesuada, pellentesque elit ut, porta massa. Quisque ullamcorper libero vitae magna bibendum vulputate. Ut aliquet ullamcorper nisl, in eleifend augue condimentum ac. Nunc venenatis vestibulum leo, in posuere velit finibus at. Integer libero velit, pharetra nec tellus sit amet, feugiat fermentum lectus. Nulla vitae vulputate nisl, ac placerat urna. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Vivamus gravida mauris ut turpis scelerisque vestibulum. Curabitur eu massa elit. Ut tristique, velit in scelerisque pellentesque, metus sem facilisis ipsum, sed varius lectus erat nec orci.
                """;
        webForm.inputTextArea(expectedText);
        String actualText = webForm.getTextAreaValue();
        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Validate DisabledInput field behavior on the WebFrom page")
    public void testWebFormDisabledInput() {
        String expectedText = "Disabled input";
        String inputText = "Web form";
        Assertions.assertAll(
                () -> Assertions.assertThrows(ElementNotInteractableException.class, () -> webForm.inputDisabledInput(inputText)),
                () -> Assertions.assertEquals(expectedText, webForm.getDisabledInputValue()),
                () -> Assertions.assertFalse(webForm.isDisabledInputActive())
        );
    }

    @Test
    @DisplayName("Validate ReadonlyInput field behavior on the WebFrom page")
    public void testWebFormReadonlyInput() {
        String expectedText = "Readonly input";
        String inputText = "Web form";
        webForm.inputReadOnlyInput(inputText);
        String actualText = webForm.getReadOnlyInputValue();
        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Validate Dropdown(Select) field behavior on the WebFrom page")
    public void testWebFormDropdownSelect() {
        String expectedText = "Two";
        webForm.selectDropdownSelect(2);
        String actualText = webForm.getDropdownSelectValue();
        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Validate Dropdown(Datalist) field behavior on the WebFrom page")
    public void testWebFormDropdownDatalist() {
        String expectedText = "Poland";
        webForm.inputDropdownDatalist(expectedText);
        String actualText = webForm.getDropdownDatalistValue();
        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Validate FileInput field behavior on the WebFrom page")
    public void testWebFormFileInput() throws URISyntaxException {
        String expectedText = "SeleniumImage.png";
        String filePath = Paths.get(ClassLoader.getSystemResource(expectedText).toURI()).toFile().getAbsolutePath();
        webForm.attachFileInput(filePath);
        String actualText = webForm.getFileInputValue();
        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Validate Checkbox field behavior on the WebFrom page")
    public void testWebFormCheckbox() {
        webForm.markDefaultCheckbox();
        Assertions.assertAll(
                () -> Assertions.assertTrue(webForm.isCheckedCheckboxMarked()),
                () -> Assertions.assertTrue(webForm.isDefaultCheckboxMarked())
        );
        webForm.markDefaultCheckbox();
        webForm.markCheckedCheckbox();
        Assertions.assertAll(
                () -> Assertions.assertFalse(webForm.isCheckedCheckboxMarked()),
                () -> Assertions.assertFalse(webForm.isDefaultCheckboxMarked())
        );
    }

    @Test
    @DisplayName("Validate Radio field behavior on the WebFrom page")
    public void testWebFormRadio() throws InterruptedException {
        webForm.markDefaultRadio();
        Assertions.assertAll(
                () -> Assertions.assertFalse(webForm.isCheckedRadioMarked()),
                () -> Assertions.assertTrue(webForm.isDefaultRadioMarked())
        );
        webForm.markCheckedRadio();
        Assertions.assertAll(
                () -> Assertions.assertTrue(webForm.isCheckedRadioMarked()),
                () -> Assertions.assertFalse(webForm.isDefaultRadioMarked())
        );
    }

    @Test
    @DisplayName("Validate Submit button behavior on the WebFrom page")
    public void testWebFormSubmit() {
        webForm.clickSubmit();
        Assertions.assertAll(
                () -> Assertions.assertEquals("Form submitted", new SubmitPage().getTitle()),
                () -> Assertions.assertEquals("Received!", new SubmitPage().getDescription())
        );
    }

    @Test
    @DisplayName("Validate ColorPicker field behavior on the WebFrom page")
    public void testWebFormColorPicker() {
        String expectedColor = "#00ff00";
        webForm.pickColorPicker(expectedColor);
        Assertions.assertEquals(expectedColor, webForm.getColorPickerValue());
    }

    @Test
    @DisplayName("Validate DatePicker field behavior on the WebFrom page")
    public void testWebFormDatePicker() {
        String expectedDate = "03/21/1989";
        webForm.inputDatePicker(expectedDate);
        Assertions.assertEquals(expectedDate, webForm.getDatePickerValue());
    }

    @Test
    @DisplayName("Validate ExampleSlider field behavior on the WebFrom page")
    public void testWebFormExampleSlider() {
        int expectedPosition = 2;
        webForm.slideExampleRange(expectedPosition);
        Assertions.assertEquals(expectedPosition, webForm.getSlideExampleRangeValue());
    }

    @Test
    @DisplayName("Validate ReturnToIndex button behavior on the WebFrom page")
    public void testWebFormReturnToIndex() {
        webForm.clickReturnToIndex();
        Assertions.assertEquals(new HomePage().URL + "index.html", driver.getCurrentUrl());
    }
}
