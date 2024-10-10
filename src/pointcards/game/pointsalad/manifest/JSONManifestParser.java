package pointcards.game.pointsalad.manifest;

import java.util.List;

import org.json.JSONObject;

import pointcards.game.pointsalad.Card;

public class JSONManifestParser {
    private final JSONCardParser cardParser;

    public JSONManifestParser(JSONObject manifest) {
        if (!this.isValid()) {
            // TODO: Create a custom exception for this
            throw new IllegalArgumentException("Invalid manifest");
        }

        this.cardParser = new JSONCardParser(manifest.getJSONObject("cards"));
    }

    private boolean isValid() {
        return true;
    }

    public List<Card> getCards() throws Exception {
        return this.cardParser.parseCards();
    }

}
