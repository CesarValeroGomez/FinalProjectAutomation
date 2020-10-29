package uiTests;

import helpers.ReadPropertiesFile;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProjectBoardPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseTest {

    protected Logger logger = LogManager.getLogger( BaseTest.class);

    private String url = "https://trello.com/";
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ProjectBoardPage projectBoardPage;
    protected ReadPropertiesFile readProperties;


    @BeforeClass
    public void beforeClass() {
        readProperties = new ReadPropertiesFile();
    }

    @BeforeMethod
    public void loadData() {
        loginPage = new LoginPage(driver);
        driver = loginPage.browserDriverConnection("CHROME"); //Could be: CHROME, FIREFOX, IE
        loginPage.visit(url);
    }


    @AfterMethod
    public void afterClass(ITestResult result) {
        takeScreenShotIfError(result);
        driver.close();
        driver.quit();
    }

    public void takeScreenShotIfError(ITestResult result) {
        if(ITestResult.FAILURE==result.getStatus()) {
            String path = "C:\\Users\\cvalero\\Projects\\FinalProjectAutamation\\test-output\\screenShots\\";
            String screenShotName = "Error_"+result.getName();
            logger.info("TestCase failed due to: "+screenShotName);
            File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs( OutputType.FILE);
            try {
                FileUtils.copyFile( screenShotFile, new File( path + screenShotName + ".png" ) );
                InputStream screenshotInputStream = new FileInputStream( screenShotFile );
                Allure.addAttachment( screenShotName, screenshotInputStream );
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Test Passed ");
        }
    }
}
