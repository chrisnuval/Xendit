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
        //cast element to wrapsDriver
        WrapsDriver wrapsDriver = (WrapsDriver) element;

        // get the entire screenshot from the driver of passed WebElement
        File screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver())
                .getScreenshotAs(OutputType.FILE);

        // create an instance of buffered image from captured screenshot
        BufferedImage image = ImageIO.read(screen);

        // get the width and height of the WebElement using getSize()
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        // create a rectangle using width and height
        Rectangle rect = new Rectangle(width, height);

        // get the location of WebElement in a Point.
        // this will provide X & Y co-ordinates of the WebElement
        Point point = element.getLocation();

        // create image  for element using its location and size.
        // this will give image data specific to the WebElement
        BufferedImage dest = image.getSubimage(point.getX(), point.getY(), rect.width,
                rect.height);

        // write back the image data for element in new File
        ImageIO.write(dest, "png", new File(imageFileLocation));


        return imageFileLocation;
    }


    public String recognizeCharacterInImage(String imageFileLocation) throws TesseractException {
        Tesseract tesseract = new Tesseract();
        return tesseract.doOCR(new File(imageFileLocation));
    }
}
