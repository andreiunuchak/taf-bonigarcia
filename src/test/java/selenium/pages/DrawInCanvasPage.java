package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class DrawInCanvasPage extends BasePage {
    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/draw-in-canvas.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byDescription = By.xpath("//p");
    private final By byCanvas = By.id("my-canvas");

    public DrawInCanvasPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open DrawInCanvasPage: " + URL)
    public DrawInCanvasPage open() {
        driver.get(URL);
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Receive Description")
    public String getDescription() {
        return driver.findElement(byDescription).getText();
    }

    @Step("Draw square")
    public DrawInCanvasPage drawZigZagLine(String direction, Point startPoint, int steps, int stepLength) {
        if (startPoint.getX() < 0 || startPoint.getY() < 0) {
            throw new IllegalArgumentException("Point X and Y coordinates should be greater than 0");
        }
        if (steps <= 0) {
            throw new IllegalArgumentException("Amount of steps should be greater than 0");
        }
        if (stepLength <= 0) {
            throw new IllegalArgumentException("Step length should be greater than 0");
        }
        Rectangle canvasRect = driver.findElement(byCanvas).getRect();
        int startX = canvasRect.getX() + startPoint.getX();
        startX = Math.min(startX, canvasRect.getX() + canvasRect.getWidth());
        int startY = canvasRect.getY() + canvasRect.getHeight() - startPoint.getY();
        startY = Math.max(startY, canvasRect.getY());
        Actions actions = new Actions(driver)
                .moveToLocation(startX, startY).clickAndHold();
        for (int i = 0; i < steps; i++) {
            switch (direction.toLowerCase()) {
                case "up" -> actions.moveByOffset(stepLength, -stepLength).moveByOffset(-stepLength, -stepLength);
                case "down" -> actions.moveByOffset(stepLength, stepLength).moveByOffset(-stepLength, stepLength);
                case "right" -> actions.moveByOffset(stepLength, stepLength).moveByOffset(stepLength, -stepLength);
                case "left" -> actions.moveByOffset(-stepLength, stepLength).moveByOffset(-stepLength, -stepLength);
                default -> {
                }
            }
        }
        actions.release().perform();
        return this;
    }

    public byte[] getCanvasImage() {
        return driver.findElement(byCanvas).getScreenshotAs(OutputType.BYTES);
    }
}
