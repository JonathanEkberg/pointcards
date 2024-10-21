package pointcards.game.pointsalad;

import java.util.List;

import pointcards.game.concepts.BaseDecks;

/**
 * The {@code Decks} class represents a collection of decks in the Point Salad
 * game.
 * It extends the {@link pointcards.game.concepts.BaseDecks} class, providing
 * additional
 * functionality specific to the Point Salad game.
 * 
 * <p>
 * This class includes methods to check if all decks are empty.
 * </p>
 * 
 * @see pointcards.game.concepts.BaseDecks
 */
public class Decks extends BaseDecks<Deck, Card> {
    /**
     * Constructs a new {@code Decks} instance with the specified list of decks.
     * 
     * @param decks The list of decks to be managed by this {@code Decks} instance.
     */
    public Decks(List<Deck> decks) {
        super(decks);
    }

    /**
     * Checks if all decks in this collection are empty.
     * 
     * @return {@code true} if all decks are empty, otherwise {@code false}.
     */
    public boolean allEmptyDecks() {
        for (Deck deck : this.getDecks()) {
            if (deck.size() != 0) {
                return false;
            }
        }

        return true;
    }
}
