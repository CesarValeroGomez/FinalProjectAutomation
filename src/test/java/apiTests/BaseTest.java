package apiTests;

import helpers.ReadPropertiesRequest;
import org.testng.annotations.BeforeClass;
import requestsManager.RequestOperations;

public abstract class BaseTest {

    protected RequestOperations requestOperations;
    protected ReadPropertiesRequest readProperties;

    @BeforeClass
    public void beforeClass() {
        requestOperations = new RequestOperations();
        requestOperations.authenticate();
        readProperties = new ReadPropertiesRequest();
    }
}
