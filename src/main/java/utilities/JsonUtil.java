package utilities;

import org.json.JSONObject;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtil {

    private static final String FILE_PATH =
            "src/main/resources/TestData.json";

    // ===================== READ JSON FILE =====================

    private static JSONObject getJsonObject() {

        try {

            String content =
                    new String(
                            Files.readAllBytes(
                                    Paths.get(FILE_PATH)
                            )
                    );

            return new JSONObject(content);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new JSONObject();
    }

    // ===================== READ NESTED DATA =====================

    public static String get(
            String parent,
            String child
    ) {

        try {

            JSONObject jsonObject =
                    getJsonObject();

            return jsonObject
                    .getJSONObject(parent)
                    .getString(child);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // ===================== WRITE NESTED DATA =====================

    public static void writeData(
            String parent,
            String child,
            String value
    ) {

        try {

            JSONObject jsonObject =
                    getJsonObject();

            JSONObject parentObject;

            if (jsonObject.has(parent)) {

                parentObject =
                        jsonObject.getJSONObject(parent);

            } else {

                parentObject =
                        new JSONObject();
            }

            parentObject.put(child, value);

            jsonObject.put(parent, parentObject);

            FileWriter file =
                    new FileWriter(FILE_PATH);

            file.write(jsonObject.toString(4));

            file.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}