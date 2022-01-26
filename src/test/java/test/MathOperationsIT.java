package test;

import net.sourceforge.tess4j.TesseractException;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.CalculatorPage;

import java.io.IOException;

public class MathOperationsIT extends BaseIT {

    @Test
    public void whenUsersAdd_ThenCalculatorSuccessfullyAdds() throws IOException, TesseractException {
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("2");
        calculatorPage.pressAdd();
        calculatorPage.pressKey("2");
        calculatorPage.pressEnter();
        calculatorPage.generateImageOfResultWithFileName("addition");
        calculatorPage.verifyIfCorrect("4");

    }

    @Ignore
    @Test
    public void whenUsersSubtract_ThenCalculatorSuccessfullySubtracts() throws IOException, TesseractException {
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("2");
        calculatorPage.pressSubtract();
        calculatorPage.pressKey("2");
        calculatorPage.pressEnter();
        calculatorPage.generateImageOfResultWithFileName("subraction");
        calculatorPage.verifyIfCorrect("0");

    }

    @Ignore
    @Test
    public void whenUsersDivide_ThenCalculatorSuccessfullyDivides() throws IOException, TesseractException {
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("2");
        calculatorPage.pressDivide();
        calculatorPage.pressKey("2");
        calculatorPage.pressEnter();
        calculatorPage.generateImageOfResultWithFileName("division");
        calculatorPage.verifyIfCorrect("1");

    }

}
