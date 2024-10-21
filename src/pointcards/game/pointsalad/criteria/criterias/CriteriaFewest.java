package pointcards.game.pointsalad.criteria.criterias;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;

/**
 * The CriteriaFewest class implements the ICriteria interface and represents a
 * scoring criterion
 * where points are awarded if the owner has the fewest number of a specific
 * veggie type compared to opponents.
 */
public class CriteriaFewest implements ICriteria {
    private final Veggie target;
    private final int points;

    /**
     * Constructs a CriteriaFewest object with the specified target veggie and
     * points.
     *
     * @param target the Veggie object representing the target veggie type
     * @param points the points awarded for meeting the criterion
     */
    public CriteriaFewest(Veggie target, int points) {
        this.target = target;
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
        int ownerCount = 0;

        // Count the occurrences of the target veggie type in the owner's cards
        for (Card card : owner) {
            if (card.getVeggie() == target) {
                ownerCount++;
            }
        }

        int minOpponentCount = Integer.MAX_VALUE;

        // Find the minimum count of the target veggie type among all opponents
        for (Card[] opponent : opponents) {
            int opponentCount = 0;
            for (Card card : opponent) {
                if (card.getVeggie() == target) {
                    opponentCount++;
                }
            }
            minOpponentCount = Math.min(minOpponentCount, opponentCount);
        }

        // Award points only if the owner's count is less than the minimum opponent
        // count
        return ownerCount < minOpponentCount ? this.points : 0;
    }

    /**
     * Returns a string representation of the criterion.
     *
     * @return a string describing the criterion
     */
    @Override
    public String toString() {
        return String.format("FEWEST %s = %d", this.target, this.points);
    }
}
