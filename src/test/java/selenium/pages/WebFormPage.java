package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;

public class WebFormPage extends BasePage {
    private final String URL = ORIGIN + "/selenium-webdriver-java/web-form.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byTextInput = By.id("my-text-id");
    private final By byPassword = By.name("my-password");
    private final By byTextArea = By.name("my-textarea");
    private final By byDisabledInput = By.name("my-disabled");
    private final By byReadOnlyInput = By.name("my-readonly");
    private final By byShadowInputValue = By.cssSelector("div");
    private final By byDropdownSelect = By.name("my-select");
    private final By byDropdownDatalist = By.name("my-datalist");
    private final By byFileInput = By.name("my-file");
    private final By byFileInputValue = By.cssSelector("span");
    private final By byCheckedCheckbox = By.id("my-check-1");
    private final By byDefaultCheckbox = By.id("my-check-2");
    private final By byCheckedRadio = By.id("my-radio-1");
    private final By byDefaultRadio = By.id("my-radio-2");
    private final By bySubmit = By.xpath("//button[@type='submit']");
    private final By byColorPicker = By.name("my-colors");
    private final By byDatePicker = By.name("my-date");
    private final By byExampleRange = By.name("my-range");
    private final By byReturnToIndex = By.xpath("//div[@class='form-group tp-align-right mt-3']");

    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open WebFormPage: " + URL)
    public WebFormPage open() {
        driver.get(URL);
        waiters.waitUntilPageLoaded();
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Input text \"{text}\" into TextInput field")
    public WebFormPage inputTextInput(String text) {
        driver.findElement(byTextInput).sendKeys(text);
        return this;
    }

    @Step("Receive text from TextInput field")
    public String getTextInputValue() {
        return driver.findElement(byTextInput).getDomProperty("value");
    }

    @Step("Input text \"{password}\" into Password field")
    public WebFormPage inputPassword(String password) {
        driver.findElement(byPassword).sendKeys(password);
        return this;
    }

    @Step("Receive text from Password field")
    public String getPasswordValue() {
        return driver.findElement(byPassword).getDomProperty("value");
    }

    @Step("Input text \"{text}\" into DisabledInput field")
    public WebFormPage inputDisabledInput(String text) {
        driver.findElement(byDisabledInput).sendKeys(text);
        return this;
    }

    @Step("Receive text from DisableInput field")
    public String getDisabledInputValue() {
        return driver.findElement(byDisabledInput).getShadowRoot().findElement(byShadowInputValue).getText();
    }

    @Step("Check if DisableInput field is active")
    public boolean isDisabledInputActive() {
        return driver.findElement(byDisabledInput).isEnabled();
    }

    @Step("Input text \"{text}\" into ReadOnly field")
    public WebFormPage inputReadOnlyInput(String text) {
        driver.findElement(byReadOnlyInput).sendKeys(text);
        return this;
    }

    @Step("Receive text from ReadOnly field")
    public String getReadOnlyInputValue() {
        return driver.findElement(byReadOnlyInput).getDomProperty("value");
    }

    @Step("Input text \"{text}\" into TextArea field")
    public WebFormPage inputTextArea(String text) {
        driver.findElement(byTextArea).sendKeys(text);
        return this;
    }

    @Step("Receive text from TextArea field")
    public String getTextAreaValue() {
        return driver.findElement(byTextArea).getDomProperty("value");
    }

    @Step("Select position \"{position}\" in the Dropdown(Select) field")
    public WebFormPage selectDropdownSelect(int position) {
        if (position < 0 || position > 3) {
            throw new IllegalArgumentException("Invalid Dropdown Select. Position must be between 0 and 3.");
        }
        Select select = new Select(driver.findElement(byDropdownSelect));
        select.selectByIndex(position);
        return this;
    }

    @Step("Receive selected option from Dropdown(Select) field")
    public String getDropdownSelectValue() {
        return new Select(driver.findElement(byDropdownSelect)).getFirstSelectedOption().getText();
    }

    @Step("Input text \"{text}\" into Dropdown(Datalist) field")
    public WebFormPage inputDropdownDatalist(String text) {
        driver.findElement(byDropdownDatalist).sendKeys(text);
        return this;
    }

    @Step("Receive text from Dropdown(Datalist) field")
    public String getDropdownDatalistValue() {
        return driver.findElement(byDropdownDatalist).getDomProperty("value");
    }

    @Step("Attach file \"{file}\" to the FileInput field")
    public WebFormPage attachFileInput(String file) {
        driver.findElement(byFileInput).sendKeys(file);
        return this;
    }

    @Step("Receive attached file name from FileInput field")
    public String getFileInputValue() {
        return driver.findElement(byFileInput).getShadowRoot().findElement(byFileInputValue).getText();
    }

    @Step("Toggle CheckedCheckbox field")
    public WebFormPage markCheckedCheckbox() {
        new Actions(driver).moveToElement(driver.findElement(byCheckedCheckbox)).perform();
        waiters.waitUntilElementClickable(byCheckedCheckbox).click();
        return this;
    }

    @Step("Check if CheckedCheckbox field is marked")
    public boolean isCheckedCheckboxMarked() {
        return waiters.waitUntilElementPresent(byCheckedCheckbox).isSelected();
    }

    @Step("Toggle DefaultCheckbox field")
    public WebFormPage markDefaultCheckbox() {
        new Actions(driver).scrollToElement(driver.findElement(byDefaultCheckbox)).perform();
        waiters.waitUntilElementClickable(byDefaultCheckbox).click();
        return this;
    }

    @Step("Check if DefaultCheckbox field is marked")
    public boolean isDefaultCheckboxMarked() {
        return driver.findElement(byDefaultCheckbox).isSelected();
    }

    @Step("Select CheckedRadio field")
    public WebFormPage markCheckedRadio() {
        new Actions(driver).moveToElement(driver.findElement(byCheckedRadio)).perform();
        waiters.waitUntilElementClickable(byCheckedRadio).click();
        return this;
    }

    @Step("Check if CheckedRadio field is selected")
    public boolean isCheckedRadioMarked() {
        return waiters.waitUntilElementPresent(byCheckedRadio).isSelected();
    }

    @Step("Select DefaultRadio field")
    public WebFormPage markDefaultRadio() {
        new Actions(driver).moveToElement(driver.findElement(byDefaultRadio)).perform();
        waiters.waitUntilElementClickable(byDefaultRadio).click();
        return this;
    }

    @Step("Check if DefaultRadio field is selected")
    public boolean isDefaultRadioMarked() {
        return waiters.waitUntilElementPresent(byDefaultRadio).isSelected();
    }

    @Step("Click Submit button")
    public SubmitPage clickSubmit() {
        new Actions(driver).moveToElement(driver.findElement(bySubmit)).perform();
        waiters.waitUntilElementClickable(bySubmit).click();
        return new SubmitPage(driver);
    }

    @Step("Set \"{hexColor}\" color into the ColorPicker field")
    public WebFormPage pickColorPicker(String hexColor) {
        driver.findElement(byColorPicker).sendKeys(hexColor);
        return this;
    }

    @Step("Receive valued from ColorPicker field")
    public String getColorPickerValue() {
        return driver.findElement(byColorPicker).getDomProperty("value");
    }

    @Step("Input \"{date}\" date into the DatePicker field")
    public WebFormPage inputDatePicker(String date) {
        driver.findElement(byDatePicker).sendKeys(date, Keys.ESCAPE);
        return this;
    }

    @Step("Receive value from DatePicker field")
    public String getDatePickerValue() {
        return driver.findElement(byDatePicker).getDomProperty("value");
    }

    @Step("Move ExampleSlide to the \"{position}\" position")
    public WebFormPage slideExampleRange(int position) {
        if (position < 0 || position > 10) {
            throw new IllegalArgumentException("Invalid SlideExample position. Position must be between 0 and 10.");
        }
        WebElement slider = driver.findElement(byExampleRange);
        int x = slider.getRect().getX();
        int y = slider.getRect().getY();
        int length = slider.getRect().getWidth();
        int max = Integer.parseInt(Objects.requireNonNull(driver.findElement(byExampleRange).getDomProperty("max")));
        int min = Integer.parseInt(Objects.requireNonNull(driver.findElement(byExampleRange).getDomProperty("min")));
        new Actions(driver).clickAndHold(slider).moveToLocation(x + length * position / (max - min), y).release().perform();
        return this;
    }

    @Step("Receive value from SlideExample field")
    public int getSlideExampleRangeValue() {
        return Integer.parseInt(Objects.requireNonNull(driver.findElement(byExampleRange).getDomProperty("value")));
    }

    @Step("Click ReturnToIndex button")
    public HomePage clickReturnToIndex() {
        new Actions(driver).moveToElement(driver.findElement(byReturnToIndex)).click().perform();
        return new HomePage(driver);
    }
}
