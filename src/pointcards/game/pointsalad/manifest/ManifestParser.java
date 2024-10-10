package pointcards.game.pointsalad.manifest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import pointcards.criteria.Criterias;
import pointcards.criteria.ICriteria;
import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criterias.CriteriaFewest;
import pointcards.game.pointsalad.criterias.CriteriaMost;

public class ManifestParser {
    private final CardParser cardParser;

    public ManifestParser(JSONObject manifest) {
        if (!this.isValid()) {
            // TODO: Create a custom exception for this
            throw new IllegalArgumentException("Invalid manifest");
        }

        this.cardParser = new CardParser(manifest.getJSONObject("cards"));
    }

    private boolean isValid() {
        return true;
    }

    public List<Card> getCards() throws Exception {
        return this.cardParser.parseCards();
    }

}
