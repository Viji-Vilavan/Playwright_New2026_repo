package utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ConfigReader {

    Properties prop;

    public ConfigReader() {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
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
