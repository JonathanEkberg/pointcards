package pointcards.game.pointsalad.criteria.criterias;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;

/**
 * The CriteriaEach class implements the ICriteria interface and represents a
 * scoring criterion
 * where points are awarded if the owner has at least one of each specified
 * veggie type.
 */
public class CriteriaEach implements ICriteria {
    private final Veggie[] targets;
    private final int points;

    /**
     * Constructs a CriteriaEach object with the specified target veggies and
     * points.
     *
     * @param targets an array of Veggie objects representing the target veggie
     *                types
     * @param points  the points awarded for meeting the criterion
     */
    public CriteriaEach(Veggie[] targets, int points) {
        this.targets = targets;
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
        int has = 0;

        // Check if the owner has at least one of each target veggie type
        for (Card card : owner) {
            for (Veggie target : targets) {
                if (card.getVeggie() == target) {
                    has++;
                    break;
                }
            }
        }

        // Award points only if the owner has all target veggie types
        return has == targets.length ? this.points : 0;
    }

    /**
     * Returns a string representation of the criterion.
     *
     * @return a string describing the criterion
     */
    @Override
    public String toString() {
        if (targets.length == 1) {
            return String.format("EACH %s = %d", targets[0].toString(), points);
        }

        String[] veggies = new String[targets.length];

        for (int i = 0; i < targets.length; i++) {
            veggies[i] = targets[i].toString();
        }

        return String.format("EACH %s = %d", String.join(" or ", veggies), points);
    }
}