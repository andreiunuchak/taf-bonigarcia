package selenium.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;
import selenium.annotations.Headed;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.DrawInCanvasPage;
import selenium.pages.HomePage;
import selenium.utils.Images;

import java.awt.image.BufferedImage;
import java.io.*;

@Headless
@Story(Namespaces.Stories.DRAW_IN_CANVAS)
public class DrawInCanvasTests extends BaseTest {

    private DrawInCanvasPage drawInCanvasPage;

    @BeforeEach
    public void openPage() {
        drawInCanvasPage = new HomePage().open().clickDrawInCanvas();
    }

    @Test
    @DisplayName("Validate the title on the DropdownMenu page")
    public void testDrawInCanvasTitle() {
        Assertions.assertEquals("Drawing in canvas", drawInCanvasPage.getTitle());
    }

    @Test
    @DisplayName("Validate the description on the DrawInCanvas page")
    public void testDrawInCanvasDescription() {
        Assertions.assertEquals("Click to draw.", drawInCanvasPage.getDescription());
    }

    @Test
    @Headed
    @DisplayName("Validate drawing in the canvas")
    public void testDrawInCanvasBehaviour() {
        Point startPoint = new Point(10, 50);
        drawInCanvasPage.drawZigZagLine("right", startPoint, 20, 20);

        BufferedImage expectedImage = Images.getBufferedImageFromFile("src/test/resources/Reference_DrawInCanvas.png");
        BufferedImage actualImage = Images.getBufferedImageFromBytes(drawInCanvasPage.getCanvasImage());
        double allowedDifference = 0.1;

        Assertions.assertTrue(Images.compareBufferedImages(expectedImage, actualImage, allowedDifference), "Images should match");
    }

}
