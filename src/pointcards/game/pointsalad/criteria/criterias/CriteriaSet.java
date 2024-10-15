package pointcards.game.pointsalad.criteria.criterias;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;

public class CriteriaSet implements ICriteria {
    private final int points;

    public CriteriaSet(int points) {
        this.points = points;
    }

    @Override
    public int calculatePoints(Card[] owner, Card[][] opponents) {
        Map<Veggie, Integer> veggieCount = new HashMap<>(owner.length);

        for (Card card : owner) {
            Veggie veggie = card.getVeggie();
            veggieCount.put(veggie, veggieCount.getOrDefault(veggie, 0) + 1);
        }

        Collection<Integer> counts = veggieCount.values();
        return counts.stream().min(Integer::compareTo).orElse(0) * this.points;
    }
}