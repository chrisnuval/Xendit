package pages;

import common.ImageProcessing;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CalculatorPage {
    WebDriver driver;
    ImageProcessing captureImage;

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.captureImage = new ImageProcessing();
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.ID, using = "fullframe")
    public WebElement calculator;

    public void pressKey(String key){ calculator.sendKeys(key); }

    public void pressEnter() { calculator.sendKeys(Keys.ENTER); }

    public void pressAdd() { calculator.sendKeys(Keys.ADD); }

    public void pressSubtract() { calculator.sendKeys(Keys.SUBTRACT); }

    public void pressDivide() { calculator.sendKeys(Keys.DIVIDE); }

    public void verifyResultIsCorrect(String imgName) {
        captureImage.generateImageOfResultWithFileName(imgName, calculator);
        Assert.assertTrue(captureImage.verifyThatImageIsCorrect(imgName));
    }

}
