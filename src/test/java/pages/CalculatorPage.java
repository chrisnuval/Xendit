package pages;

import common.CharacterRecognition;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

public class CalculatorPage {
    WebDriver driver;
    CharacterRecognition captureImage;
    String imgFileLocation;

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.captureImage = new CharacterRecognition();
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.ID, using = "fullframe")
    public WebElement calculator;

    public void pressKey(String key){ calculator.sendKeys(key); }

    public void pressEnter() { calculator.sendKeys(Keys.ENTER); }

    public void pressAdd() { calculator.sendKeys(Keys.ADD); }

    public void pressSubtract() { calculator.sendKeys(Keys.SUBTRACT); }

    public void pressDivide() { calculator.sendKeys(Keys.DIVIDE); }

    public void generateImageOfResultWithFileName(String imgName) throws IOException {
        imgFileLocation = captureImage.captureImageOf(calculator, imgName);
    }

    public void verifyIfCorrect(String expected) throws TesseractException {
        String actual = captureImage.recognizeCharacterInImage(imgFileLocation);
        Assert.assertEquals(expected,actual);
    }


}
