package pointcards.game.pointsalad.criteria.criterias;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;

/**
 * The CriteriaSet class implements the ICriteria interface and represents a
 * scoring criterion
 * where points are awarded based on the number of complete sets of different
 * veggie types in the owner's cards.
 */
public class CriteriaSet implements ICriteria {
    private final int points;

    /**
     * Constructs a CriteriaSet object with the specified points.
     *
     * @param points the points awarded for each complete set of different veggie
     *               types
     */
    public CriteriaSet(int points) {
        this.points = points;
    }

    /**
     * Calculates the points based on the owner's cards.
     *
     * @param owner     an array of Card objects representing the owner's cards
     * @param opponents an array of arrays of Card objects representing the
     *                  opponents' cards (unused)
     * @return the total points earned based on the criterion
     */
    @Override
    public int calculatePoints(Card[] owner, Card[][] opponents) {
        Map<Veggie, Integer> veggieCount = new HashMap<>(owner.length);

        // Count the occurrences of each veggie type in the owner's cards
        for (Card card : owner) {
            Veggie veggie = card.getVeggie();
            veggieCount.put(veggie, veggieCount.getOrDefault(veggie, 0) + 1);
        }

        // Find the minimum count among all veggie types to determine the number of
        // complete sets
        Collection<Integer> counts = veggieCount.values();
        return counts.stream().min(Integer::compareTo).orElse(0) * this.points;
    }

    /**
     * Returns a string representation of the criterion.
     *
     * @return a string describing the criterion
     */
    @Override
    public String toString() {
        return String.format("SET = %d", this.points);
    }
}