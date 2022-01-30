package common;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.WrapsElement;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CharacterRecognition {
    String currentDirectory = System.getProperty("user.dir");

    public void generateImageOfResultWithFileName(String imgName, WebElement webElement) throws IOException, InterruptedException {
        Thread.sleep(1000);

        String imageFileLocation = currentDirectory + "/src/test/resources/actualImages/"+ imgName + ".png";
        WebDriver webDriver = ((WrapsDriver)((WrapsElement)webElement).getWrappedElement()).getWrappedDriver();

        File screen = ((TakesScreenshot) webDriver)
                .getScreenshotAs(OutputType.FILE);

        BufferedImage image = ImageIO.read(screen);

        int width = webElement.getSize().getWidth();
        int height = webElement.getSize().getHeight();

        Rectangle rect = new Rectangle(width, height);

        Point point = webElement.getLocation();

        BufferedImage dest = image.getSubimage(point.getX(), point.getY(), rect.width,
                rect.height);

        ImageIO.write(dest, "png", new File(imageFileLocation));

    }

    public void verifyThatImageIsCorrect(String imgName) throws IOException {
        String expectedImageLocation = currentDirectory + "/src/test/resources/expectedImages/" + imgName + ".png";
        String actualImageLocation = currentDirectory + "/src/test/resources/actualImages/" + imgName + ".png";

        BufferedImage expected = ImageIO.read(new File(expectedImageLocation));
        BufferedImage actual = ImageIO.read(new File(actualImageLocation));
        int w1 = expected.getWidth();
        int w2 = actual.getWidth();
        int h1 = expected.getHeight();
        int h2 = actual.getHeight();
        if ((w1 != w2) || (h1 != h2)) {
            System.out.println("Both images should have same dimensions");
        } else {
            long diff = 0;
            for (int j = 0; j < h1; j++) {
                for (int i = 0; i < w1; i++) {
                    int pixel1 = expected.getRGB(i, j);
                    Color color1 = new Color(pixel1, true);
                    int r1 = color1.getRed();
                    int g1 = color1.getGreen();
                    int b1 = color1.getBlue();
                    int pixel2 = actual.getRGB(i, j);
                    Color color2 = new Color(pixel2, true);
                    int r2 = color2.getRed();
                    int g2 = color2.getGreen();
                    int b2 = color2.getBlue();


                    long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
                    diff = diff + data;
                }
            }

            long avg = diff / (w1 * h1 * 3);
            long percentage = (avg / 255) * 100;

            Assert.assertEquals(0, percentage);
        }
    }
}
