package pointcards.game.pointsalad.criteria.criterias;

import java.util.HashMap;

import pointcards.criteria.ICriteria;
import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;

public class CriteriaAtLeast implements ICriteria {
    private final int atLeastAmount;
    private final int points;

    public CriteriaAtLeast(int atLeastAmount, int points) {
        this.atLeastAmount = atLeastAmount;
        this.points = points;
    }

    @Override
    public int calculatePoints(Card[] owner, Card[][] __) {
        int veggiesWithAtLeastAmount = 0;
        HashMap<Veggie, Integer> veggieCount = new HashMap<>(owner.length);

        for (Card card : owner) {
            Veggie veggie = card.getVeggie();
            veggieCount.put(veggie, veggieCount.getOrDefault(veggie, 0) + 1);
        }

        for (int count : veggieCount.values()) {
            if (count >= this.atLeastAmount) {
                veggiesWithAtLeastAmount++;
            }
        }

        return veggiesWithAtLeastAmount * this.points;
    }
}