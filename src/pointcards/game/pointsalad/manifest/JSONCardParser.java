package pointcards.game.pointsalad.manifest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.CriteriaType;
import pointcards.game.pointsalad.criteria.Criterias;
import pointcards.game.pointsalad.criteria.ICriteria;
import pointcards.game.pointsalad.criteria.criterias.CriteriaAtLeast;
import pointcards.game.pointsalad.criteria.criterias.CriteriaEach;
import pointcards.game.pointsalad.criteria.criterias.CriteriaEven;
import pointcards.game.pointsalad.criteria.criterias.CriteriaFewest;
import pointcards.game.pointsalad.criteria.criterias.CriteriaFewestTotal;
import pointcards.game.pointsalad.criteria.criterias.CriteriaMost;
import pointcards.game.pointsalad.criteria.criterias.CriteriaMostTotal;
import pointcards.game.pointsalad.criteria.criterias.CriteriaOdd;
import pointcards.game.pointsalad.criteria.criterias.CriteriaPer;
import pointcards.game.pointsalad.criteria.criterias.CriteriaPerMissingType;
import pointcards.game.pointsalad.criteria.criterias.CriteriaSet;

public class JSONCardParser {
    private static final int EXPECTED_CARD_AMOUNT = 108;
    private final JSONObject cards;

    public JSONCardParser(JSONObject cards) {
        this.cards = cards;
    }

    public List<Card> parseCards() {
        List<Card> parsedCards = new ArrayList<>(EXPECTED_CARD_AMOUNT);

        for (String key : this.cards.keySet()) {
            Veggie veggie = Veggie.valueOf(key);
            parsedCards.addAll(this.parseCardsArray(veggie, this.cards.getJSONArray(key)));
        }

        if (parsedCards.size() != EXPECTED_CARD_AMOUNT) {
            System.err.printf("Expected %d cards in manifest data but only found %d\n", EXPECTED_CARD_AMOUNT,
                    parsedCards.size());
            System.exit(1);
        }

        return parsedCards;
    }

    private List<Card> parseCardsArray(Veggie veggie, JSONArray cards) {
        try {
            List<Card> parsedCards = new ArrayList<>(cards.length());

            for (int i = 0; i < cards.length(); i++) {
                JSONObject card = cards.getJSONObject(i);
                parsedCards.add(this.parseCard(veggie, card));
            }

            return parsedCards;
        } catch (JSONException e) {
            e.printStackTrace();
            System.err.printf("Error parsing cards for veggie: %s. Message: %s\n", veggie, e.getMessage());
            System.exit(1);
            return null;
        }
    }

    private Card parseCard(Veggie veggie, JSONObject card) {
        if (!card.has("criterias")) {
            System.out.println(card);
            // TODO: Create a custom exception for this
            // throw new Exception("Card is missing criteria");
            System.exit(1);
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
            parsedCriterias[i] = parsedCriteria;
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
            case MOST: {
                int points = criteria.getNumber("points").intValue();
                Veggie target = Veggie.valueOf(criteria.getString("veggie"));
                return new CriteriaMost(target, points);
            }
            case FEWEST: {
                int points = criteria.getNumber("points").intValue();
                Veggie target = Veggie.valueOf(criteria.getString("veggie"));
                return new CriteriaFewest(target, points);
            }
            case EVEN: {
                int points = criteria.getNumber("points").intValue();
                Veggie target = Veggie.valueOf(criteria.getString("veggie"));
                return new CriteriaEven(target, points);
            }
            case ODD: {
                int points = criteria.getNumber("points").intValue();
                Veggie target = Veggie.valueOf(criteria.getString("veggie"));
                return new CriteriaOdd(target, points);
            }
            case PER: {
                int points = criteria.getNumber("points").intValue();
                Veggie target = Veggie.valueOf(criteria.getString("veggie"));
                return new CriteriaPer(target, points);
            }
            case EACH: {
                int points = criteria.getNumber("points").intValue();
                var jsonVeggies = (criteria.getJSONArray("veggies"));
                Veggie[] veggies = new Veggie[jsonVeggies.length()];

                for (int i = 0; i < jsonVeggies.length(); i++) {
                    veggies[i] = Veggie.valueOf(jsonVeggies.getString(i));
                }

                return new CriteriaEach(veggies, points);
            }
            case SET: {
                int points = criteria.getNumber("points").intValue();
                return new CriteriaSet(points);
            }
            case MOST_TOTAL: {
                int points = criteria.getNumber("points").intValue();
                return new CriteriaMostTotal(points);
            }
            case AT_LEAST: {
                int points = criteria.getNumber("points").intValue();
                int value = criteria.getNumber("value").intValue();
                return new CriteriaAtLeast(value, points);
            }
            case FEWEST_TOTAL: {
                int points = criteria.getNumber("points").intValue();
                return new CriteriaFewestTotal(points);
            }
            case PER_MISSING_TYPE: {
                int points = criteria.getNumber("points").intValue();
                return new CriteriaPerMissingType(points);
            }
            default:
                System.err.printf("Unimplemented criteria for CriteriaEnum: %s\n", criteriaType);
                System.exit(1);
                return null;
        }
    }
}
