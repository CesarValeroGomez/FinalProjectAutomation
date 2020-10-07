package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadPropertiesFile {

    private static String user;
    private static String password;
    private static String userName;

    public ReadPropertiesFile() {

        try {
            FileInputStream fis = new FileInputStream("./src/main/resources/data/authentication.properties");

            Properties prop = new Properties();
            prop.load(fis);

            user = prop.getProperty("usuario");
            password = prop.getProperty("clave");
            userName = prop.getProperty("userName");

        } catch (FileNotFoundException ex) {
            System.out.println("Error reading the File");
            Logger.getLogger(ReadPropertiesFile.class.getName()).log( Level.SEVERE, null, ex);
        } catch (IOException e) {  e.printStackTrace();       }
    }

    public String getUser()     { return user;      }
    public String getPassword() { return password;  }
    public String getUserName() { return userName;  }
}
