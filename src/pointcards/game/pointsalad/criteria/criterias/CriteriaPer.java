package pointcards.game.pointsalad.criteria.criterias;

import pointcards.criteria.ICriteria;
import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;

public class CriteriaPer implements ICriteria {
    private final Veggie target;
    private final int points;

    public CriteriaPer(Veggie target, int points) {
        this.target = target;
        this.points = points;
    }

    @Override
    public int calculatePoints(Card[] owner, Card[][] __) {
        int count = 0;

        for (Card card : owner) {
            if (card.getVeggie() == target) {
                count++;
            }
        }

        return count * this.points;
    }
}