package pointcards.game.pointsalad.criteria;

import pointcards.game.pointsalad.Card;

/**
 * The {@code ICriteria} interface defines the methods for calculating points
 * based on specific criteria. Implementations of this interface represent
 * different scoring rules that can be applied to a player's hand of cards.
 */
public interface ICriteria {
    /**
     * Calculates the points earned based on the owner's cards and the opponents'
     * cards.
     *
     * @param owner     the cards owned by the player
     * @param opponents the cards owned by the opponents
     * @return the points earned based on the criteria
     */
    int calculatePoints(Card[] owner, Card[][] opponents);

    /**
     * Returns a string representation of the criteria.
     *
     * @return a string representation of the criteria
     */
    String toString();
}
