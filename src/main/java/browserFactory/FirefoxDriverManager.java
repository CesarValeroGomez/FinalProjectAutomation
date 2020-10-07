package browserFactory;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/browserDrivers/geckodriver.exe");
        driver = new FirefoxDriver();
    }
}
