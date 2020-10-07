package pageObjects;

import browserFactory.DriverManager;
import browserFactory.DriverManagerFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;

public class BasePage {

    private DriverManager driverManager;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver browserDriverConnection(String browser) {
        driverManager = DriverManagerFactory.getManager(browser);
        driver = driverManager.getDriver();
        return driver;
    }

    public void visit(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        PageFactory.initElements(driver,this);
    }

    public void takeScreenShot(String screenShotName) {
        String path = "C:\\Users\\cvalero\\Projects\\FinalProjectAutamation\\test-output\\screenShots\\";
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File screenshotFile = screenshot.getScreenshotAs( OutputType.FILE);
        try{
            FileUtils.copyFile(screenshotFile, new File(path+screenShotName+".png"));
            InputStream screenshotInputStream = new FileInputStream(screenshotFile);
            Allure.addAttachment(screenShotName,screenshotInputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void waitForElementToBeLoaded (WebElement element){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout( Duration.ofSeconds(120))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring( NoSuchElementException.class);
        wait.until( ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element){
        Wait <WebDriver> wait = new FluentWait <>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
