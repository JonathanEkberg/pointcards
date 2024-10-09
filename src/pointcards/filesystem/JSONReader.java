package pointcards.filesystem;

import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class JSONReader {
    private final String manifestPath;

    public JSONReader(String manifestPath) {
        this.manifestPath = manifestPath;
    }

    public String getManifestPath() {
        return manifestPath;
    }

    public JSONObject readJsonManifest() throws FileNotFoundException {
        InputStream fileStream = new FileInputStream(this.manifestPath);
        Scanner scanner = new Scanner(fileStream, "UTF-8").useDelimiter("\\A");

        // Read the entire JSON file into a String
        String jsonString = scanner.hasNext() ? scanner.next() : "";

        // Parse the JSON string into a JSONObject
        JSONObject jsonObject = new JSONObject(jsonString);
        scanner.close();

        return jsonObject;
    }
}
