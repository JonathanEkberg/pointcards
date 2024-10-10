package pointcards.game.pointsalad.criterias;

import pointcards.criteria.ICriteria;
import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;

public class CriteriaFewestTotal implements ICriteria {
    private final int points;

    public CriteriaFewestTotal(int points) {
        this.points = points;
    }

    @Override
    public int calculatePoints(Card[] owner, Card[][] opponents) {
        int ownerCount = 0;
        for (Card __ : owner) {
            ownerCount++;
        }

        int minOpponentCount = Integer.MAX_VALUE;
        for (Card[] opponent : opponents) {
            int opponentCount = 0;
            for (Card __ : opponent) {
                opponentCount++;
            }
            minOpponentCount = Math.min(minOpponentCount, opponentCount);
        }

        return ownerCount < minOpponentCount ? this.points : 0;
    }
}
