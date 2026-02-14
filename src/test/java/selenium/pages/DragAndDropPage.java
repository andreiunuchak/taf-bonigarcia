package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropPage extends AbstractPage {

    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byDragElement = By.id("draggable");
    private final By byDropElement = By.id("target");


    @Override
    @Step("Open DragAndDropPage:" + URL)
    public DragAndDropPage open() {
        driver.get(URL);
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Receive DragElement position")
    public Point getDragElementCenterPoint() {
        Rectangle elementRect = driver.findElement(byDragElement).getRect();
        return new Point(elementRect.getX() + elementRect.getWidth() / 2, elementRect.getY() + elementRect.getHeight() / 2);
    }

    @Step("Receive DropElement position")
    public Point getDropElementCenterPoint() {
        Rectangle elementRect = driver.findElement(byDropElement).getRect();
        return new Point(elementRect.getX() + elementRect.getWidth() / 2, elementRect.getY() + elementRect.getHeight() / 2);
    }

    @Step("Drag element from {startPoint} to {endPoint}")
    public void dragAndDrop(Point startPoint, Point endPoint) {
        new Actions(driver).moveToLocation(startPoint.getX(), startPoint.getY()).clickAndHold().moveToLocation(endPoint.getX(), endPoint.getY()).release().perform();
    }
}
