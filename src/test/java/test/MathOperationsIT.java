package test;

import org.testng.annotations.Test;
import pages.CalculatorPage;

public class MathOperationsIT extends BaseIT {

    @Test
    public void whenUsersAdd_ThenCalculatorSuccessfullyAdds() {
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("2");
        calculatorPage.pressAdd();
        calculatorPage.pressKey("2");
        calculatorPage.pressEnter();
        calculatorPage.verifyResultIsCorrect("4");
    }

    @Test
    public void whenUsersSubtract_ThenCalculatorSuccessfullySubtracts() {
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("2");
        calculatorPage.pressSubtract();
        calculatorPage.pressKey("2");
        calculatorPage.pressEnter();
        calculatorPage.verifyResultIsCorrect("0");
    }

    @Test
    public void whenUsersDivide_ThenCalculatorSuccessfullyDivides() {
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("2");
        calculatorPage.pressDivide();
        calculatorPage.pressKey("2");
        calculatorPage.pressEnter();
        calculatorPage.verifyResultIsCorrect("1");
    }





}
