package utilities;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class DataReader {

    static JSONObject data;

    static {

        try {

            String content = new String(
                    Files.readAllBytes(Paths.get("resources/testData.json")));

            data = new JSONObject(content);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static String getCollectionName() {

        return data.getString("collectionName");
    }

    public static String getEmail() {

        return data.getString("email");
    }

    public static String getPassword() {

        return data.getString("password");
    }

}