package browserFactory;

import org.openqa.selenium.ie.InternetExplorerDriver;

public class IEdriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        System.setProperty("webdriver.ie.driver", "./src/main/resources/browserDrivers/IEDriverServer.exe");
        driver = new InternetExplorerDriver();
    }
}
