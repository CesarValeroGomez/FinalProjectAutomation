package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadPropertiesRequest {

    private static String domain;
    private static String apiKey;
    private static String token;
    private static String idBoard;
    private static String idListBacklog;
    private static String idListDesign;
    private static String idListToDo;
    private static String idListDoing;
    private static String idListCodeReview;
    private static String idListTesting;
    private static String idListDone;


    public ReadPropertiesRequest() {

        try {
            FileInputStream fis = new FileInputStream("./src/main/resources/data/requestData.properties");

            Properties prop = new Properties();
            prop.load(fis);

            domain = prop.getProperty("domain");
            apiKey = prop.getProperty("apiKey");
            token = prop.getProperty("token");
            idBoard = prop.getProperty("idBoard");
            idListBacklog = prop.getProperty("idListBacklog");
            idListDesign = prop.getProperty("idListDesign");
            idListToDo = prop.getProperty("idListToDo");
            idListDoing = prop.getProperty("idListDoing");
            idListCodeReview = prop.getProperty("idListCodeReview");
            idListTesting = prop.getProperty("idListTesting");
            idListDone = prop.getProperty("idListDone");

        } catch (FileNotFoundException ex) {
            System.out.println("Error reading the File");
            Logger.getLogger(ReadPropertiesRequest.class.getName()).log( Level.SEVERE, null, ex);
        } catch (IOException e) {  e.printStackTrace();       }
    }

    public String getDomain() {
        return domain;
    }
    public String getApiKey() {
        return apiKey;
    }
    public String getToken() {
        return token;
    }
    public String getIdBoard() {
        return idBoard;
    }
    public String getIdListBacklog() {
        return idListBacklog;
    }
    public String getIdListDesign() {
        return idListDesign;
    }
    public String getIdListToDo() {
        return idListToDo;
    }
    public String getIdListDoing() {
        return idListDoing;
    }
    public String getIdListCodeReview() {
        return idListCodeReview;
    }
    public String getIdListTesting() {
        return idListTesting;
    }
    public String getIdListDone() {
        return idListDone;
    }
}
