package selenium.utils;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Images {

    public static boolean compareBufferedImages(BufferedImage image1, BufferedImage image2, double allowedDifference) {
        ImageComparisonResult result = new ImageComparison(image1, image2)
                .setAllowingPercentOfDifferentPixels(allowedDifference)
                .compareImages();
        return result.getImageComparisonState() == ImageComparisonState.MATCH;
    }

    public static BufferedImage getBufferedImageFromFile(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Files.newInputStream(Path.of(path)));
        } catch (IOException _) {
        }
        return image;
    }

    public static BufferedImage getBufferedImageFromBytes(byte[] bytes) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException _) {
        }
        return image;
    }
}
