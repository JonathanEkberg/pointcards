package pointcards.game.pointsalad.criteria;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;

public class CriteriaFewest implements ICriteria {
    private final Veggie target;
    private final int points;

    public CriteriaFewest(Veggie target, int points) {
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

        int minOpponentCount = Integer.MAX_VALUE;
        for (Card[] opponent : opponents) {
            int opponentCount = 0;
            for (Card card : opponent) {
                if (card.getVeggie() == target) {
                    opponentCount++;
                }
            }
            minOpponentCount = Math.min(minOpponentCount, opponentCount);
        }

        return ownerCount < minOpponentCount ? this.points : 0;
    }
}
