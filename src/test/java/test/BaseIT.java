package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseIT {
    WebDriver driver;

    @BeforeMethod
    public void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1400x900");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.online-calculator.com/full-screen-calculator/");
    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String failSafeDirectory = currentDirectory + "/target/failsafe-reports/";
        String fileNameWithMethodAndInstance = result.getMethod().getMethodName() + result.getMethod().getInstance().toString();
        Reporter.setCurrentTestResult(result);


        if (result.getStatus() == 2) {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(failSafeDirectory + "FAILED_screenshot_" + fileNameWithMethodAndInstance +".png");

            FileUtils.copyFile(srcFile,destFile);
        }

        driver.quit();
    }



}
