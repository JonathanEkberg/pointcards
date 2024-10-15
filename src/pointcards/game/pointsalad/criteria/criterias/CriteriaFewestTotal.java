package pointcards.game.pointsalad.criteria.criterias;

import pointcards.game.criteria.ICriteria;
import pointcards.game.pointsalad.Card;

public class CriteriaFewestTotal implements ICriteria {
    private final int points;

    public CriteriaFewestTotal(int points) {
        this.points = points;
    }

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
}
