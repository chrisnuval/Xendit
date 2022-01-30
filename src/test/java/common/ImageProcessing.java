package common;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.WrapsElement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;

public class ImageProcessing {
    String currentDirectory = System.getProperty("user.dir");

    public void generateImageOfResultWithFileName(String imgName, WebElement webElement) {
        try {
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
        } catch(Exception e) {
            System.out.println(e);
        }


    }

    public boolean verifyThatImageIsCorrect(String imgName) {
        String expectedImageLocation = currentDirectory + "/src/test/resources/expectedImages/" + imgName + ".png";
        String actualImageLocation = currentDirectory + "/src/test/resources/actualImages/" + imgName + ".png";

        try {
            BufferedImage expectedBufferImage = ImageIO.read(new File(expectedImageLocation));
            DataBuffer expectedDataBuffer = expectedBufferImage.getData().getDataBuffer();
            int expectedDataBufferSize = expectedDataBuffer.getSize();

            BufferedImage actualBufferedImage = ImageIO.read(new File(actualImageLocation));
            DataBuffer actualDataBuffer = actualBufferedImage.getData().getDataBuffer();
            int actualDataBufferSize = actualDataBuffer.getSize();

            if (expectedDataBufferSize == actualDataBufferSize) {
                for (int i = 0; i < expectedDataBufferSize; i++) {
                    if (expectedDataBuffer.getElem(i) != actualDataBuffer.getElem(i)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
