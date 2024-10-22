package pointcards.game.pointsalad.participants;

import pointcards.game.pointsalad.concepts.Hand;

/**
 * The IPlayer interface represents a player in the Point Salad game.
 * It provides methods to get the player's name and hand.
 */
public interface IPlayer {

    /**
     * Gets the name of the player.
     *
     * @return the name of the player as a String.
     */
    public String getName();

    /**
     * Gets the hand of the player.
     *
     * @return the player's hand as a Hand object.
     */
    public Hand getHand();
}
