package Config;

import java.io.FileInputStream;
import java.util.Properties;

public class configReader {

    Properties prop;

    public configReader() {

        try {

            prop = new Properties();

            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");

            prop.load(fis);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public String getBaseURL() {
        return prop.getProperty("baseURL");
    }

    public String getUsername() {
        return prop.getProperty("username");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }

}