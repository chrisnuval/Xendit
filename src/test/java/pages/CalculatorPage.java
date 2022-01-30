package pages;

import common.CharacterRecognition;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;

public class CalculatorPage {
    WebDriver driver;
    CharacterRecognition captureImage;

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

    public void verifyResultIsCorrect(String imgName) throws IOException, InterruptedException {
        captureImage.generateImageOfResultWithFileName(imgName, calculator);
        captureImage.verifyThatImageIsCorrect(imgName);
    }

}
