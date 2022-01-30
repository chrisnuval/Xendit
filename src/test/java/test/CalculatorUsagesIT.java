package test;

import org.testng.annotations.Test;
import pages.CalculatorPage;

public class CalculatorUsagesIT extends BaseIT {
    @Test
    public void whenUserPressesDigits_ThenCalculatorMaxInputIsNine(){
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("123456789");
        calculatorPage.verifyResultIsCorrect("123456789");
        calculatorPage.pressKey("1234567890");
        calculatorPage.verifyResultIsCorrect("123456789");
    }

    @Test
    public void calculatorCanSupportNegativeValues(){
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("0");
        calculatorPage.pressSubtract();
        calculatorPage.pressKey("123456789");
        calculatorPage.pressEnter();
        calculatorPage.verifyResultIsCorrect("-123456789");
    }

    @Test
    public void calculatorCanSupportDecimalNumbers(){
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("1.5");
        calculatorPage.pressAdd();
        calculatorPage.pressKey("2.2");
        calculatorPage.pressEnter();
        calculatorPage.verifyResultIsCorrect("3.7");
    }

    @Test
    public void whenUserTriesToPressDecimalPointMultipleTimes_ThenOnlyOneDecimalPointIsEntered(){
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("1.....5");
        calculatorPage.verifyResultIsCorrect("1.5");
    }


    @Test
    public void whenUserPressesEquals_ThenCalculatorReplicatesLastAction() {
        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.pressKey("2");
        calculatorPage.pressAdd();
        calculatorPage.pressKey("2");
        calculatorPage.pressEnter();
        calculatorPage.verifyResultIsCorrect("4");
        calculatorPage.pressEnter();
        calculatorPage.verifyResultIsCorrect("6");
    }
}
