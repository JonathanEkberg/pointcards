package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import pointcards.game.pointsalad.criteria.CriteriaFewest;

public class JSONManifestParser {
    private static final int EXPECTED_CARD_AMOUNT = 108;

    private final JSONObject manifest;

    public JSONManifestParser(JSONObject manifest) {
        this.manifest = manifest;
    }

    public List<Card> getCards() throws Exception {
        List<Card> parsedCards = new ArrayList<>(EXPECTED_CARD_AMOUNT);
        JSONObject cards = manifest.getJSONObject("cards");

        for (Veggie veggie : Veggie.values()) {
            if (cards.has(veggie.toString())) {
                System.out.println("Parsing " + veggie.toString());
                JSONArray veggieCards = cards.getJSONArray(veggie.toString());
                var parsed = this.parseCards(veggie, veggieCards);

                for (Card card : parsed) {
                    parsedCards.add(card);
                }
            }
        }

        // if (parsedCards.size() != EXPECTED_CARD_AMOUNT) {
        // // TODO: Create a custom exception for this
        // throw new Exception("Expected " + EXPECTED_CARD_AMOUNT + " cards, but found "
        // + parsedCards.size());
        // }

        return parsedCards;
    }

    private Card[] parseCards(Veggie veggie, JSONArray cards) {
        Card[] parsedCards = new Card[cards.length()];

        for (int i = 0; i < cards.length(); i++) {
            JSONObject card = cards.getJSONObject(i);
            this.parseCard(veggie, card);
        }

        return parsedCards;
    }

    private Card parseCard(Veggie veggie, JSONObject card) {
        // if (!card.has("criteria")) {
        // System.out.println("Card is missing criteria");
        // return null;
        // }

        return new Card(veggie, new CriteriaFewest(veggie, 5));
    }
}
