package pointcards.game.pointsalad.criteria.criterias;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;

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