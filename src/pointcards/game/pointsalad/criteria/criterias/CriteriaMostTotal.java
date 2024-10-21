package pointcards.game.pointsalad.criteria.criterias;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.criteria.ICriteria;

/**
 * The CriteriaMostTotal class implements the ICriteria interface and represents
 * a scoring criterion
 * where points are awarded if the owner has the most total number of cards
 * compared to opponents.
 */
public class CriteriaMostTotal implements ICriteria {
    private final int points;

    /**
     * Constructs a CriteriaMostTotal object with the specified points.
     *
     * @param points the points awarded for meeting the criterion
     */
    public CriteriaMostTotal(int points) {
        this.points = points;
    }

    /**
     * Calculates the points based on the owner's cards and the opponents' cards.
     *
     * @param owner     an array of Card objects representing the owner's cards
     * @param opponents an array of arrays of Card objects representing the
     *                  opponents' cards
     * @return the total points earned based on the criterion
     */
    @Override
    public int calculatePoints(Card[] owner, Card[][] opponents) {
        int ownerCount = owner.length;

        // Check if any opponent has an equal or greater number of cards
        for (Card[] cards : opponents) {
            if (cards.length >= ownerCount) {
                return 0;
            }
        }

        // Award points if the owner has the most total number of cards
        return this.points;
    }

    /**
     * Returns a string representation of the criterion.
     *
     * @return a string describing the criterion
     */
    @Override
    public String toString() {
        return String.format("MOST TOTAL = %d", this.points);
    }
}