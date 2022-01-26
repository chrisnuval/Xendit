package common;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CharacterRecognition {

    public String captureImageOf(WebElement element, String imgName) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String imageFileLocation = currentDirectory + "/src/test/resources/"+ imgName + ".png";

        WrapsDriver wrapsDriver = (WrapsDriver) element;


        File screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver())
                .getScreenshotAs(OutputType.FILE);

        BufferedImage image = ImageIO.read(screen);

        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        Rectangle rect = new Rectangle(width, height);

        Point point = element.getLocation();

        BufferedImage dest = image.getSubimage(point.getX(), point.getY(), rect.width,
                rect.height);

        ImageIO.write(dest, "png", new File(imageFileLocation));


        return imageFileLocation;
    }


    public String recognizeCharacterInImage(String imageFileLocation) throws TesseractException {
        Tesseract tesseract = new Tesseract();
        return tesseract.doOCR(new File(imageFileLocation));
    }
}
