package pointcards.game.pointsalad.criteria.criterias;

import java.util.HashMap;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;

/**
 * The CriteriaPerMissingType class implements the ICriteria interface and
 * represents a scoring criterion
 * where points are awarded for each missing type of veggie in the owner's
 * cards.
 */
public class CriteriaPerMissingType implements ICriteria {
    private final int points;

    /**
     * Constructs a CriteriaPerMissingType object with the specified points.
     *
     * @param points the points awarded for each missing type of veggie
     */
    public CriteriaPerMissingType(int points) {
        this.points = points;
    }

    /**
     * Calculates the points based on the owner's cards.
     *
     * @param owner an array of Card objects representing the owner's cards
     * @param __    an unused parameter
     * @return the total points earned based on the criterion
     */
    @Override
    public int calculatePoints(Card[] owner, Card[][] __) {
        HashMap<Veggie, Integer> veggieCount = new HashMap<>(owner.length);

        // Count the occurrences of each veggie type in the owner's cards
        for (Card card : owner) {
            Veggie veggie = card.getVeggie();
            veggieCount.put(veggie, veggieCount.getOrDefault(veggie, 0) + 1);
        }

        // Calculate the total points based on the number of missing veggie types
        return (Veggie.values().length - veggieCount.size()) * this.points;
    }

    /**
     * Returns a string representation of the criterion.
     *
     * @return a string describing the criterion
     */
    @Override
    public String toString() {
        return String.format("PER MISSING TYPE = %d", this.points);
    }
}