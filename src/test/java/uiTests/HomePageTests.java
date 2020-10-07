package uiTests;

import helpers.ReadPropertiesFile;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;


public class HomePageTests extends BaseTest {


    @Test(priority = 1, description = "User can Login in the Page")
    public void validateLogin() {
        loginPage.setDoLogin(readProperties.getUser(), readProperties.getPassword());
        Assert.assertEquals(loginPage.validateUser(), readProperties.getUserName());
        logger.info("UI Test 1 - Passed - validateLogin ");
    }

    @Test(priority = 2, description = "validate main components in the HomePage")
    public void validateHomePage() {
        loginPage.setDoLogin(readProperties.getUser(), readProperties.getPassword());
        homePage = new HomePage(driver);
        homePage.validateTemplates();
        logger.info("UI Test 2 - Passed - validateHomePage - expected components were displayed ");
    }

    @Test(priority = 3, description = "Validate user get into the Project")
    public void validatePersonalProject() {
        String projectName = "New Final Project";
        loginPage.setDoLogin(readProperties.getUser(), readProperties.getPassword());
        homePage = new HomePage(driver);
        Assert.assertEquals(homePage.personalKanban(projectName), projectName);
        logger.info("UI Test 3 - Passed - validatePersonalProject "+projectName+" Exists");
    }

    @Test(priority = 4, description = "Take ScreenShot when there is an Error.")
    public void validateScreenShotError() {
        Assert.assertEquals(loginPage.validateErrorCapture(), "Log Out");
        logger.info("UI Test 4 - Passed - validateScreenShotError");
    }
}
