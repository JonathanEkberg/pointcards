package pointcards.game.pointsalad;

import pointcards.game.concepts.BaseCard;
import pointcards.game.pointsalad.criteria.ICriteria;

public class Card extends BaseCard {
    private final Veggie veggie;
    private final ICriteria criteria;

    public Card(Veggie veggie, ICriteria criteria) {
        this.veggie = veggie;
        this.criteria = criteria;
    }

    public Veggie getVeggie() {
        return veggie;
    }

    public ICriteria getCriteria() {
        return criteria;
    }

    public String toString() {
        return veggie.toString();
    }
}
