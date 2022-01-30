package test;

import org.testng.annotations.Test;
import pages.CalculatorPage;

import java.io.IOException;

public class MathOperationsIT extends BaseIT {

    @Test
    public void whenUsersAdd_ThenCalculatorSuccessfullyAdds() throws IOException, InterruptedException {
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("2");
        calculatorPage.pressAdd();
        calculatorPage.pressKey("2");
        calculatorPage.pressEnter();
        calculatorPage.verifyResultIsCorrect("addition");
    }


    @Test
    public void whenUsersSubtract_ThenCalculatorSuccessfullySubtracts() throws IOException, InterruptedException {
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("2");
        calculatorPage.pressSubtract();
        calculatorPage.pressKey("2");
        calculatorPage.pressEnter();
        calculatorPage.verifyResultIsCorrect("subraction");

    }


    @Test
    public void whenUsersDivide_ThenCalculatorSuccessfullyDivides() throws IOException, InterruptedException {
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("2");
        calculatorPage.pressDivide();
        calculatorPage.pressKey("2");
        calculatorPage.pressEnter();
        calculatorPage.verifyResultIsCorrect("division");

    }

}
