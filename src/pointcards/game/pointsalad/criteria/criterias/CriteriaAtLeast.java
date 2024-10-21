package pointcards.game.pointsalad.criteria.criterias;

import java.util.HashMap;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;

/**
 * The CriteriaAtLeast class implements the ICriteria interface and represents a
 * scoring criterion
 * where points are awarded based on having at least a certain number of a
 * specific type of veggie.
 */
public class CriteriaAtLeast implements ICriteria {
    private final int atLeastAmount;
    private final int points;

    /**
     * Constructs a CriteriaAtLeast object with the specified minimum amount and
     * points.
     *
     * @param atLeastAmount the minimum number of a specific veggie type required to
     *                      earn points
     * @param points        the points awarded for meeting the criterion
     */
    public CriteriaAtLeast(int atLeastAmount, int points) {
        this.atLeastAmount = atLeastAmount;
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
        int veggiesWithAtLeastAmount = 0;
        HashMap<Veggie, Integer> veggieCount = new HashMap<>(owner.length);

        // Count the occurrences of each veggie type in the owner's cards
        for (Card card : owner) {
            Veggie veggie = card.getVeggie();
            veggieCount.put(veggie, veggieCount.getOrDefault(veggie, 0) + 1);
        }

        // Count how many veggie types meet or exceed the atLeastAmount
        for (int count : veggieCount.values()) {
            if (count >= this.atLeastAmount) {
                veggiesWithAtLeastAmount++;
            }
        }

        // Calculate the total points based on the number of veggie types meeting the
        // criterion
        return veggiesWithAtLeastAmount * this.points;
    }

    /**
     * Returns a string representation of the criterion.
     *
     * @return a string describing the criterion
     */
    @Override
    public String toString() {
        return String.format(">= %d VEGGIE TYPE = %d", this.atLeastAmount, this.points);
    }
}