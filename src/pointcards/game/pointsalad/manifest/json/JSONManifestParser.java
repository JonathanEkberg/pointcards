package pointcards.game.pointsalad.manifest.json;

import java.util.List;

import org.json.JSONObject;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.manifest.json.versions.Version;

public class JSONManifestParser {
    private final JSONObject manifest;
    private final IJSONCardParser cardParser;

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

    public static Version getVersion(JSONObject manifest) {
        int version = manifest.getJSONObject("meta").getNumber("version").intValue();
        return Version.intVersionToVersion(version);
    }

    public Version getVersion() {
        return JSONManifestParser.getVersion(this.manifest);
    }

    private boolean isValid() {
        return true;
    }

    public List<Card> getCards() throws Exception {
        return this.cardParser.parseCards();
    }

}
