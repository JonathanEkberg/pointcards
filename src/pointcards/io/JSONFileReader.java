package pointcards.io;

import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * The JSONFileReader class provides methods for reading JSON files.
 */
public class JSONFileReader {
    /**
     * Reads a JSON file and returns its content as a JSONObject.
     * 
     * @param filePath The path to the JSON file.
     * @return The content of the JSON file as a JSONObject.
     * @throws FileNotFoundException If the file is not found.
     */
    public JSONObject readJSONFile(String filePath) throws FileNotFoundException {
        InputStream is = new FileInputStream(filePath);
        Scanner scanner = new Scanner(is).useDelimiter("\\A");
        String jsonText = scanner.hasNext() ? scanner.next() : "";
        scanner.close();
        return new JSONObject(jsonText);
    }
}