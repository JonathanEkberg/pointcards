package pointcards.game.pointsalad.criteria.criterias;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;

public class CriteriaMost implements ICriteria {
    private final Veggie target;
    private final int points;

    public CriteriaMost(Veggie target, int points) {
        this.target = target;
        this.points = points;
    }

    @Override
    public int calculatePoints(Card[] owner, Card[][] opponents) {
        int ownerCount = 0;
        for (Card card : owner) {
            if (card.getVeggie() == target) {
                ownerCount++;
            }
        }

        // If the owner has no cards of the target type then they cannot have the most
        if (ownerCount == 0) {
            return 0;
        }

        int maxOpponentCount = 0;
        for (Card[] opponent : opponents) {
            int opponentCount = 0;
            for (Card card : opponent) {
                if (card.getVeggie() == target) {
                    opponentCount++;
                }
            }
            maxOpponentCount = Math.max(maxOpponentCount, opponentCount);
        }

        return ownerCount > maxOpponentCount ? this.points : 0;

    }

    public String toString() {
        return String.format("MOST %s = %d", target.toString(), points);
    }
}
