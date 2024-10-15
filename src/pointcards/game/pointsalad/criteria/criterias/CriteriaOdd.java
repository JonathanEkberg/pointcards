package pointcards.game.pointsalad.criteria.criterias;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;

public class CriteriaOdd implements ICriteria {
    private final Veggie target;
    private final int points;

    public CriteriaOdd(Veggie target, int points) {
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

        return ownerCount % 2 != 0 ? this.points : 0;
    }
}
