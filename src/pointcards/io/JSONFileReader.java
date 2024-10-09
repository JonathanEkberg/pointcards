package pointcards.io;

import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class JSONFileReader {
    private final String filePath;

    public JSONFileReader(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public JSONObject readJsonManifest() throws FileNotFoundException {
        InputStream fileStream = new FileInputStream(this.filePath);
        Scanner scanner = new Scanner(fileStream, "UTF-8").useDelimiter("\\A");

        // Read the entire JSON file into a String
        String jsonString = scanner.hasNext() ? scanner.next() : "";

        // Parse the JSON string into a JSONObject
        JSONObject jsonObject = new JSONObject(jsonString);
        scanner.close();

        return jsonObject;
    }
}
