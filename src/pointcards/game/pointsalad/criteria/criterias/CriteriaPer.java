package pointcards.game.pointsalad.criteria.criterias;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;

/**
 * The CriteriaPer class implements the ICriteria interface and represents a
 * scoring criterion
 * where points are awarded for each occurrence of a specific veggie type.
 */
public class CriteriaPer implements ICriteria {
    private final Veggie target;
    private final int points;

    /**
     * Constructs a CriteriaPer object with the specified target veggie and points.
     *
     * @param target the Veggie object representing the target veggie type
     * @param points the points awarded for each occurrence of the target veggie
     *               type
     */
    public CriteriaPer(Veggie target, int points) {
        this.target = target;
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
        int count = 0;

        // Count the occurrences of the target veggie type in the owner's cards
        for (Card card : owner) {
            if (card.getVeggie() == target) {
                count++;
            }
        }

        // Calculate the total points based on the count of the target veggie type
        return count * this.points;
    }

    /**
     * Returns a string representation of the criterion.
     *
     * @return a string describing the criterion
     */
    @Override
    public String toString() {
        return String.format("PER %s = %d", this.target, this.points);
    }
}