package pointcards.game.pointsalad;

import pointcards.game.pointsalad.criteria.ICriteria;

public class Card {
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
}
