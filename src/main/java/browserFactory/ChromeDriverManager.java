package browserFactory;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager {

    @Override
    public void createDriver() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
    }
}