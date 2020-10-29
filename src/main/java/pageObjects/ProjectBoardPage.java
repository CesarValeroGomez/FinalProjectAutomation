package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProjectBoardPage extends BasePage {

    @FindBy(css = ".js-board-editing-target")
    protected WebElement boardTitle;
    @FindBy(css = ".js-list:nth-child(2) .js-add-another-card")
    protected WebElement addCardInDesign;
    @FindBy(css = ".list-card-composer-textarea")
    protected WebElement taskTextArea;
    protected WebElement newCardCreated;
    protected WebElement oldCardElement;
    protected WebElement oldCardElementMoved;
    @FindBy(xpath = "//div[@class='description-content js-desc-content']")
    protected WebElement descriptionSelect;
    @FindBy(css = ".card-description")
    protected WebElement descriptionTextfield;
    @FindBy(css = ".mod-submit-edit")
    protected WebElement submitDescription;
    @FindBy(css = ".comment-box-input")
    protected WebElement commentBoxInput;
    @FindBy(css = ".mod-no-top-bottom-margin")
    protected WebElement submitComment;
    @FindBy(css = ".current-comment > p")
    protected WebElement currentComment;
    @FindBy(css = ".js-move-card")
    protected WebElement moveCardOption;
    @FindBy(css = ".js-select-list")
    protected WebElement moveCardToList;
    @FindBy(css = ".wide")
    protected WebElement submitMove;
    @FindBy(css = ".icon-md")
    protected WebElement exitCard;

    protected WebElement divDoing;

    public ProjectBoardPage(WebDriver driver) {
        super( driver );
        PageFactory.initElements(driver,this);
    }

    public String CreateNewCard(String cardName) {
        addCardInDesign.click();
        waitForElementToBeLoaded(taskTextArea);
        taskTextArea.click();
        taskTextArea.sendKeys(cardName);
        taskTextArea.sendKeys(Keys.ENTER);
        newCardCreated = driver.findElement( By.xpath(String.format("//span[contains(.,'%s')]",cardName)));
        waitForElementToBeLoaded(newCardCreated);
        return newCardCreated.getText();
    }

    public String UpdateDescription(String cardName, String description) {
        oldCardElement = driver.findElement( By.xpath(String.format("//span[contains(.,'%s')]",cardName)));
        oldCardElement.click();
        waitForElementToBeLoaded(descriptionSelect);
        descriptionSelect.click();
        descriptionTextfield.click();
        descriptionTextfield.clear();
        descriptionTextfield.sendKeys(description);
        submitDescription.click();
        waitForElementToBeLoaded(descriptionSelect);
        return descriptionSelect.getText();
    }

    public String AddCommment(String cardName, String comment) {
        oldCardElement = driver.findElement(By.xpath(String.format("//span[contains(.,'%s')]",cardName)));
        oldCardElement.click();
        waitForElementToBeLoaded(commentBoxInput);
        commentBoxInput.click();
        commentBoxInput.sendKeys(comment);
        submitComment.click();
        waitForElementToBeLoaded(currentComment);
        takeScreenShot("Comment added ");
        return currentComment.getText();
    }

    public Boolean MoveCardTo(String cardName, String listName) {
        String listLocator = String.format("//h2[contains(text(),'%s')]",listName);
        String listContent = "//ancestor::div[@class='list js-list-content']";
        String cardLocator = String.format("//span[contains(.,'%s')]",cardName);
        oldCardElement = driver.findElement(By.xpath(cardLocator));
        oldCardElement.click();
        waitForElementToBeLoaded(moveCardOption);
        moveCardOption.click();
        moveCardToList.click();
        moveCardToList.sendKeys(listName);
        moveCardToList.sendKeys(Keys.ENTER);
        submitMove.click();
        exitCard.click();
        waitForElementToBeLoaded(boardTitle);
        takeScreenShot("Card was successfully Moved to: "+listName);
        oldCardElementMoved = driver.findElement(By.xpath(listLocator+listContent+cardLocator));
        return oldCardElementMoved.isDisplayed();
    }

    public Boolean CardWasMoved(String cardName, String listName) {
        String listLocator = String.format("//h2[contains(text(),'%s')]",listName);
        String listContent = "//ancestor::div[@class='list js-list-content']";
        String cardLocator = String.format("//span[contains(.,'%s')]",cardName);
        divDoing = driver.findElement(By.xpath(listLocator+listContent));
        oldCardElementMoved = divDoing.findElement(By.xpath(cardLocator));
        return oldCardElementMoved.isDisplayed();
    }
}
