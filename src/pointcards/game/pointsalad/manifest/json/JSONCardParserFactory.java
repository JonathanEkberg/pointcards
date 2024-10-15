package pointcards.game.pointsalad.manifest.json;

import org.json.JSONObject;

import pointcards.game.pointsalad.manifest.json.versions.Version;
import pointcards.game.pointsalad.manifest.json.versions.v1.JSONCardParser;

public class JSONCardParserFactory {
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
