package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "g > path:nth-child(1)")
    protected WebElement trelloImg;
    @FindBy(xpath = "//a[contains(@href, '/login')]")
    protected WebElement buttonLogin;
    @FindBy(id = "user")
    protected WebElement userLogin;
    @FindBy(id = "password")
    protected WebElement passwordLogin;
    @FindBy(id = "login")
    protected WebElement doLogin;
    @FindBy(css = ".css-t5emrf > span:nth-child(1)")
    protected WebElement doSecondLogin;
    @FindBy(css = ".\\_24AWINHReYjNBf")
    protected WebElement userLogo;
    @FindBy(css = ".\\_1njv2a9PIrnydF")
    protected WebElement userName;


    public LoginPage(WebDriver driver) {
        super( driver );
    }

    public void setDoLogin (String user, String password) {
        waitForElementToBeLoaded(trelloImg);
        buttonLogin.click();
        userLogin.sendKeys(user);
        passwordLogin.sendKeys(password);
        doLogin.click();

        waitForElementToBeLoaded(passwordLogin);
        waitForElementToBeClickable(doSecondLogin);
        passwordLogin.sendKeys(password);
        doSecondLogin.click();

    }

    public String validateUser()
    {
        waitForElementToBeLoaded(userLogo);
        userLogo.click();
        takeScreenShot("User authenticated");
        return userName.getText();
    }

    public String validateErrorCapture() {
        waitForElementToBeLoaded(trelloImg);
        return buttonLogin.getText();
    }
}
