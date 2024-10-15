package pointcards.game.pointsalad.manifest.json;

import java.util.List;

import org.json.JSONObject;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.manifest.json.versions.Version;
import pointcards.game.pointsalad.manifest.json.versions.v1.JSONCardParser;

public class JSONManifestParser {
    private final JSONObject manifest;
    private final JSONCardParser cardParser;

    public JSONManifestParser(JSONObject manifest) {
        if (!this.isValid()) {
            // TODO: Create a custom exception for this
            throw new IllegalArgumentException("Invalid manifest");
        }

        this.manifest = manifest;
        this.cardParser = new JSONCardParser(manifest.getJSONObject("cards"));
    }

    public Version getVersion() {
        int version = this.manifest.getJSONObject("meta").getNumber("version").intValue();
        return Version.intVersionToVersion(version);
    }

    private boolean isValid() {
        return true;
    }

    public List<Card> getCards() throws Exception {
        return this.cardParser.parseCards();
    }

}
