package uiTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProjectBoardPage;

public class ProjectBoardTest extends BaseTest {

    @Test(priority = 1, description = "Validate user can create a task card in the Board")
    public void CreateNewCard() {
        String cardName = "New develop task";
        loginPage.setDoLogin(readProperties.getUser(), readProperties.getPassword());
        homePage = new HomePage(driver);
        homePage.personalProject("New Final Project");
        projectBoardPage = new ProjectBoardPage(driver);
        Assert.assertEquals(projectBoardPage.CreateNewCard(cardName), cardName);
        logger.info("UI Test 5 - Passed - CreateNewCard ");
        logger.info("Card Created with name: "+cardName);
    }

    @Test(priority = 2, description = "Update the Description in the Card")
    public void UpdateDescription() {
        String cardName = "Old develop task";
        String description = "Update the Description in task";
        loginPage.setDoLogin(readProperties.getUser(), readProperties.getPassword());
        homePage = new HomePage(driver);
        homePage.personalProject("New Final Project");
        projectBoardPage = new ProjectBoardPage(driver);
        Assert.assertEquals(projectBoardPage.UpdateDescription(cardName, description), description);
        logger.info("UI Test 6 - Passed - UpdateDescription ");
    }

    @Test(priority = 3, description = "Add a comment in the Card")
    public void AddCommentCard() {
        String cardName = "Old develop task";
        String comment = "Write any a comment in this space";
        loginPage.setDoLogin(readProperties.getUser(), readProperties.getPassword());
        homePage = new HomePage(driver);
        homePage.personalProject("New Final Project");
        projectBoardPage = new ProjectBoardPage(driver);
        Assert.assertEquals(projectBoardPage.AddCommment(cardName, comment), comment);
        logger.info("UI Test 7 - Passed - AddCommentCard ");
    }

    @Test(priority = 4, description = "Move the Card to other List")
    public void MoveCardToList() {
        String cardName = "Old develop task";
        loginPage.setDoLogin(readProperties.getUser(), readProperties.getPassword());
        homePage = new HomePage(driver);
        homePage.personalProject("New Final Project");
        projectBoardPage = new ProjectBoardPage(driver);
        Assert.assertTrue(projectBoardPage.MoveCardTo(cardName, "Doing"));
        logger.info("UI Test 8 - Passed - MoveCardToList: "+" Doing");
    }

    @Test(priority = 5, description = "Validate The card was moved with the second way.")
    public void ValidateCardWasMoved() {
        String cardName = "Old develop task";
        loginPage.setDoLogin(readProperties.getUser(), readProperties.getPassword());
        homePage = new HomePage(driver);
        homePage.personalProject("New Final Project");
        projectBoardPage = new ProjectBoardPage(driver);
        Assert.assertTrue(projectBoardPage.CardWasMoved(cardName, "Doing"));
        logger.info("UI Test 9 - Passed - ValidateCardWasMoved: "+" Doing");
    }
}