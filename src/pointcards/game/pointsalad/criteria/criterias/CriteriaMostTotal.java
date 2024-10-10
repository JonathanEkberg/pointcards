package pointcards.game.pointsalad.criteria.criterias;

import pointcards.criteria.ICriteria;
import pointcards.game.pointsalad.Card;

public class CriteriaMostTotal implements ICriteria {
    private final int points;

    public CriteriaMostTotal(int points) {
        this.points = points;
    }

    @Override
    public int calculatePoints(Card[] owner, Card[][] opponents) {
        int ownerCount = owner.length;

        for (Card[] cards : opponents) {
            if (cards.length >= ownerCount) {
                return 0;
            }
        }

        return this.points;
    }
}