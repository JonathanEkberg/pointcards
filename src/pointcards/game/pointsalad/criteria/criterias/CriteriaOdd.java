package pointcards.game.pointsalad.criteria.criterias;

import pointcards.game.pointsalad.concepts.Card;
import pointcards.game.pointsalad.concepts.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;

/**
 * The CriteriaOdd class implements the ICriteria interface and represents a
 * scoring criterion
 * where points are awarded if the owner has an odd number of a specific veggie
 * type.
 */
public class CriteriaOdd implements ICriteria {
    private final Veggie target;
    private final int points;

    /**
     * Constructs a CriteriaOdd object with the specified target veggie and points.
     *
     * @param target the Veggie object representing the target veggie type
     * @param points the points awarded for meeting the criterion
     */
    public CriteriaOdd(Veggie target, int points) {
        this.target = target;
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
        int ownerCount = 0;

        // Count the occurrences of the target veggie type in the owner's cards
        for (Card card : owner) {
            if (card.getVeggie() == target) {
                ownerCount++;
            }
        }

        // Award points only if the count is odd
        return ownerCount % 2 != 0 ? this.points : 0;
    }

    /**
     * Returns a string representation of the criterion.
     *
     * @return a string describing the criterion
     */
    @Override
    public String toString() {
        return String.format("ODD %s = %d", this.target, this.points);
    }
}
