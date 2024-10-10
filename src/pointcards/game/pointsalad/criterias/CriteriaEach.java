package pointcards.game.pointsalad.criterias;

import pointcards.criteria.ICriteria;
import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;

public class CriteriaEach implements ICriteria {
    private final Veggie[] targets;
    private final int points;

    public CriteriaEach(Veggie[] targets, int points) {
        this.targets = targets;
        this.points = points;
    }

    @Override
    public int calculatePoints(Card[] owner, Card[][] __) {
        int has = 0;

        for (Card card : owner) {
            for (Veggie target : targets) {
                if (card.getVeggie() == target) {
                    has++;
                    break;
                }
            }
        }

        return has == targets.length ? this.points : 0;
    }
}