package pointcards.game.pointsalad.manifest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.Criterias;
import pointcards.game.pointsalad.criteria.ICriteria;

public class CardParser {
    private static final int EXPECTED_CARD_AMOUNT = 108;
    private final JSONObject cards;

    public CardParser(JSONObject cards) {
        this.cards = cards;
    }

    public List<Card> parseCards() {
        List<Card> parsedCards = new ArrayList<>(EXPECTED_CARD_AMOUNT);

        for (String key : this.cards.keySet()) {
            Veggie veggie = Veggie.valueOf(key);
            List<Card> cards = this.parseCardsArray(veggie, this.cards.getJSONArray(key));
        }

        return parsedCards;
    }

    private List<Card> parseCardsArray(Veggie veggie, JSONArray cards) {
        List<Card> parsedCards = new ArrayList<>(cards.length());

        for (int i = 0; i < cards.length(); i++) {
            JSONObject card = cards.getJSONObject(i);
            this.parseCard(veggie, card);
        }

        return parsedCards;
    }

    private Card parseCard(Veggie veggie, JSONObject card) {
        if (!card.has("criteria")) {
            // TODO: Create a custom exception for this
            throw new Exception("Card is missing criteria");
        }

        JSONArray criterias = card.getJSONArray("criterias");

        if (criterias.length() == 0) {
            JSONObject criteria = criterias.getJSONObject(0);
            return new Card(veggie, this.parseCriteria(criteria));
        }

        ICriteria[] parsedCriterias = new ICriteria[criterias.length()];

        for (int i = 0; i < criterias.length(); i++) {
            JSONObject criteria = criterias.getJSONObject(i);
            ICriteria parsedCriteria = this.parseCriteria(criteria);
        }

        ICriteria criteria = new Criterias(parsedCriterias);

        return new Card(veggie, criteria);
    }

    private ICriteria parseCriteria(JSONObject criteria) {
        if (!criteria.has("type")) {
            return null;
        }

        String type = criteria.getString("type");

        CriteriaType criteriaType = CriteriaType.valueOf(type);

        switch (criteriaType) {
            case FEWEST:
                return this.parseCriteriaFewest(criteria);
            case MOST:
                return this.parseCriteriaMost(criteria);
            case ODD:
                return this.parseCriteriaOdd(criteria);
            case EVEN:
                return this.parseCriteriaEven(criteria);
            case EACH:
                return this.parseCriteriaEach(criteria);
            default:
                return null;
        }
    }
}
