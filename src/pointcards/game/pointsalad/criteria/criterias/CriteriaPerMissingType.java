package pointcards.game.pointsalad.criteria.criterias;

import java.util.HashMap;

import pointcards.criteria.ICriteria;
import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;

public class CriteriaPerMissingType implements ICriteria {
    private final int points;

    public CriteriaPerMissingType(int points) {
        this.points = points;
    }

    @Override
    public int calculatePoints(Card[] owner, Card[][] __) {
        HashMap<Veggie, Integer> veggieCount = new HashMap<>(owner.length);

        for (Card card : owner) {
            Veggie veggie = card.getVeggie();
            veggieCount.put(veggie, veggieCount.getOrDefault(veggie, 0) + 1);
        }

        return (Veggie.values().length - veggieCount.size()) * this.points;
    }
}