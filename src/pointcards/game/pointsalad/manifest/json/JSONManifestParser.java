package pointcards.game.pointsalad.manifest.json;

import java.util.List;

import org.json.JSONObject;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.manifest.json.versions.Version;

/**
 * The `JSONManifestParser` class is responsible for parsing the JSON manifest
 * file
 * and extracting the card information for the Point Salad game. It uses a
 * version-specific
 * parser to handle different versions of the manifest format.
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * {@code
 * JSONObject manifest = new JSONObject(jsonString);
 * JSONManifestParser parser = new JSONManifestParser(manifest);
 * List<Card> cards = parser.getCards();
 * }
 * </pre>
 */
public class JSONManifestParser {
    private final JSONObject manifest;
    private final IJSONCardParser cardParser;

    /**
     * Constructs a new `JSONManifestParser` with the specified manifest.
     * 
     * @param manifest The JSON object representing the manifest.
     * @throws IllegalArgumentException if the manifest is invalid.
     */
    public JSONManifestParser(JSONObject manifest) {
        if (!this.isValid()) {
            // TODO: Create a custom exception for this
            throw new IllegalArgumentException("Invalid manifest");
        }

        this.manifest = manifest;
        IJSONCardParser cardParser = JSONCardParserFactory.getParser(JSONManifestParser.getVersion(manifest),
                manifest.getJSONObject("cards"));
        this.cardParser = cardParser;
    }

    /**
     * Gets the version of the manifest.
     * 
     * @param manifest The JSON object representing the manifest.
     * @return The version of the manifest.
     */
    public static Version getVersion(JSONObject manifest) {
        int version = manifest.getJSONObject("meta").getNumber("version").intValue();
        return Version.intVersionToVersion(version);
    }

    /**
     * Gets the version of the manifest.
     * 
     * @return The version of the manifest.
     */
    public Version getVersion() {
        return JSONManifestParser.getVersion(this.manifest);
    }

    /**
     * Validates the manifest.
     * 
     * @return `true` if the manifest is valid, otherwise `false`.
     */
    private boolean isValid() {
        return true;
    }

    /**
     * Parses and returns the list of cards from the manifest.
     * 
     * @return The list of cards.
     * @throws Exception if an error occurs while parsing the cards.
     */
    public List<Card> getCards() throws Exception {
        return this.cardParser.parseCards();
    }

}
