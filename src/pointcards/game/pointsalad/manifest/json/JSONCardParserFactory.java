package pointcards.game.pointsalad.manifest.json;

import org.json.JSONObject;

import pointcards.game.pointsalad.manifest.json.versions.Version;
import pointcards.game.pointsalad.manifest.json.versions.v1.JSONCardParser;

/**
 * The `JSONCardParserFactory` class provides a method for creating instances of
 * `IJSONCardParser`
 * based on the version of the JSON manifest. This allows for flexible handling
 * of different
 * manifest formats.
 */
public class JSONCardParserFactory {
    /**
     * Returns an instance of `IJSONCardParser` based on the specified version and
     * JSON object.
     * 
     * @param version The version of the manifest.
     * @param cards   The JSON object representing the cards.
     * @return An instance of `IJSONCardParser`.
     */
    public static IJSONCardParser getParser(Version version, JSONObject cards) {
        switch (version) {
            case V1:
                return new JSONCardParser(cards);
            default:
                assert false : "Unknown manifest version " + version;
                return null;
        }
    }
}
