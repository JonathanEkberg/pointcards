package pointcards.game.pointsalad.criteria.criterias;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.criteria.ICriteria;

/**
 * The CriteriaFewestTotal class implements the ICriteria interface and
 * represents a scoring criterion
 * where points are awarded if the owner has the fewest total number of cards
 * compared to opponents.
 */
public class CriteriaFewestTotal implements ICriteria {
    private final int points;

    /**
     * Constructs a CriteriaFewestTotal object with the specified points.
     *
     * @param points the points awarded for meeting the criterion
     */
    public CriteriaFewestTotal(int points) {
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

        int minOpponentCount = Integer.MAX_VALUE;
        for (Card[] opponent : opponents) {
            int opponentCount = opponent.length;
            minOpponentCount = Math.min(minOpponentCount, opponentCount);
        }

        return ownerCount < minOpponentCount ? this.points : 0;
    }

    /**
     * Returns a string representation of the criterion.
     *
     * @return a string describing the criterion
     */
    @Override
    public String toString() {
        return String.format("FEWEST TOTAL = %d", this.points);
    }
}
