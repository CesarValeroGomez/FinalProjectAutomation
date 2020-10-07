package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage extends BasePage {

    @FindBy(css = ".\\_1MjVHpnUnwLGMg:nth-child(1) .\\_1YJAm5f3ZMuCCb")
    protected WebElement templateProjectManagement;
    @FindBy(css = ".\\_1MjVHpnUnwLGMg:nth-child(2) .\\_1YJAm5f3ZMuCCb")
    protected WebElement templateKanban;
    @FindBy(css = ".\\_1MjVHpnUnwLGMg:nth-child(3) .\\_1YJAm5f3ZMuCCb")
    protected WebElement templateSimpleProject;
    @FindBy(css = ".\\_1MjVHpnUnwLGMg:nth-child(4) .\\_1YJAm5f3ZMuCCb")
    protected WebElement templateRemoteTeam;
    protected WebElement personalProject;
    @FindBy(css = ".js-board-editing-target")
    protected WebElement boardTitle;
    @FindBy(css = ".MEu8ZECLGMLeab")
    protected WebElement boardIcon;
    @FindBy(css = ".\\_2Z9q8nPvS1HJuT")
    protected WebElement searchProject;


    public HomePage(WebDriver driver) {
        super( driver );
        PageFactory.initElements(driver,this);
    }

    public void validateTemplates() {
        waitForElementToBeLoaded(templateProjectManagement);
        templateProjectManagement.isDisplayed();
        templateKanban.isDisplayed();
        templateSimpleProject.isDisplayed();
        templateRemoteTeam.isDisplayed();
        takeScreenShot("HomePage");
    }

    public String personalKanban(String projectName) {
        waitForElementToBeLoaded(templateProjectManagement);
        personalProject = driver.findElement(By.xpath(String.format("//div[@title='%s']", projectName)));
        waitForElementToBeLoaded(personalProject);
        personalProject.click();
        waitForElementToBeLoaded(boardTitle);
        takeScreenShot("Current Project displayed");
        boardTitle.isDisplayed();
        return boardTitle.getText();
    }

    public void personalProject(String projectName) {
        waitForElementToBeLoaded(boardIcon);
        boardIcon.click();
        waitForElementToBeLoaded(searchProject);
        searchProject.click();
        searchProject.sendKeys(projectName);
        searchProject.sendKeys(Keys.ENTER);
        waitForElementToBeLoaded(boardTitle);
        Assert.assertEquals(boardTitle.getText(), projectName);
    }

}
