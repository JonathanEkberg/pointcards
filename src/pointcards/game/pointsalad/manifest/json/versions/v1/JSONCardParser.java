package pointcards.game.pointsalad.manifest.json.versions.v1;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pointcards.game.pointsalad.concepts.Card;
import pointcards.game.pointsalad.concepts.Veggie;
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
import pointcards.game.pointsalad.manifest.json.IJSONCardParser;

/**
 * The {@code JSONCardParser} class is responsible for parsing JSON objects into
 * {@code Card} objects.
 * It handles the extraction and conversion of criteria from the JSON
 * representation.
 */
public class JSONCardParser implements IJSONCardParser {
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

    /**
     * Parses a JSON object representing a card and returns a {@code Card} object.
     *
     * @param veggie the veggie associated with the card
     * @param card   the JSON object representing the card
     * @return the parsed {@code Card} object
     */
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

    /**
     * Extracts the points value from a criteria JSON object.
     *
     * @param criteria the JSON object representing the criteria
     * @return the points value of the criteria
     */
    private int getCriteriaPoints(JSONObject criteria) {
        assert criteria.has("points") : "Criteria is missing points field";

        return criteria.getNumber("points").intValue();
    }

    /**
     * Extracts the veggie value from a criteria JSON object.
     *
     * @param criteria the JSON object representing the criteria
     * @return the veggie value of the criteria
     */
    private Veggie getCriteriaVeggie(JSONObject criteria) {
        assert criteria.has("veggie") : "Criteria is missing veggie field";

        return Veggie.valueOf(criteria.getString("veggie"));
    }

    /**
     * Parses a JSON object representing a criteria and returns an {@code ICriteria}
     * object.
     *
     * @param criteria the JSON object representing the criteria
     * @return the parsed {@code ICriteria} object, or {@code null} if the criteria
     *         type is not specified
     */
    private ICriteria parseCriteria(JSONObject criteria) {
        if (!criteria.has("type")) {
            return null;
        }

        String type = criteria.getString("type");

        CriteriaType criteriaType = CriteriaType.valueOf(type);

        switch (criteriaType) {
            case SET:
                return new CriteriaSet(getCriteriaPoints(criteria));
            case MOST_TOTAL:
                return new CriteriaMostTotal(getCriteriaPoints(criteria));
            case FEWEST_TOTAL:
                return new CriteriaFewestTotal(getCriteriaPoints(criteria));
            case PER_MISSING_TYPE:
                return new CriteriaPerMissingType(getCriteriaPoints(criteria));
            case MOST:
                return new CriteriaMost(getCriteriaVeggie(criteria), getCriteriaPoints(criteria));
            case FEWEST:
                return new CriteriaFewest(getCriteriaVeggie(criteria), getCriteriaPoints(criteria));
            case EVEN:
                return new CriteriaEven(getCriteriaVeggie(criteria), getCriteriaPoints(criteria));
            case ODD:
                return new CriteriaOdd(getCriteriaVeggie(criteria), getCriteriaPoints(criteria));
            case PER:
                return new CriteriaPer(getCriteriaVeggie(criteria), getCriteriaPoints(criteria));
            case AT_LEAST: {
                int value = criteria.getNumber("value").intValue();
                return new CriteriaAtLeast(value, getCriteriaPoints(criteria));
            }
            case EACH: {
                var jsonVeggies = (criteria.getJSONArray("veggies"));
                Veggie[] veggies = new Veggie[jsonVeggies.length()];

                for (int i = 0; i < jsonVeggies.length(); i++) {
                    veggies[i] = Veggie.valueOf(jsonVeggies.getString(i));
                }

                return new CriteriaEach(veggies, getCriteriaPoints(criteria));
            }
            default:
                System.err.printf("Unimplemented criteria for CriteriaEnum: %s\n", criteriaType);
                System.exit(1);
                return null;
        }
    }
}
