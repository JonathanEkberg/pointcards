package pointcards.game.pointsalad;

import pointcards.game.concepts.BaseCard;
import pointcards.game.pointsalad.criteria.ICriteria;

/**
 * The Card class represents a card in the Point Salad game.
 * Each card has a specific vegetable and a criteria for scoring points.
 * This class extends the BaseCard class.
 */
public class Card extends BaseCard {
    private final Veggie veggie;
    private final ICriteria criteria;

    /**
     * Constructs a new Card with the specified vegetable and criteria.
     *
     * @param veggie   the vegetable associated with this card
     * @param criteria the criteria for scoring points with this card
     */
    public Card(Veggie veggie, ICriteria criteria) {
        this.veggie = veggie;
        this.criteria = criteria;
    }

    /**
     * Returns the vegetable associated with this card.
     *
     * @return the vegetable associated with this card
     */
    public Veggie getVeggie() {
        return veggie;
    }

    /**
     * Returns the criteria for scoring points with this card.
     *
     * @return the criteria for scoring points with this card
     */
    public ICriteria getCriteria() {
        return criteria;
    }

    /**
     * Returns a string representation of this card.
     * The string representation is the string representation of the vegetable.
     *
     * @return a string representation of this card
     */
    public String toString() {
        return veggie.toString();
    }
}
