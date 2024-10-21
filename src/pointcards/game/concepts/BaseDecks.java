package pointcards.game.concepts;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code BaseDecks} class represents a collection of decks in the game.
 * This class provides methods to manage and interact with multiple decks.
 *
 * @param <D> The type of deck, which extends {@link BaseDeck}.
 * @param <C> The type of card, which extends {@link BaseCard}.
 */
public class BaseDecks<D extends BaseDeck<C>, C extends BaseCard> {
    private final List<D> decks;

    /**
     * Constructs a {@code BaseDecks} instance with the specified list of decks.
     *
     * @param decks The list of decks to be managed by this instance.
     */
    public BaseDecks(List<D> decks) {
        List<D> decksList = new ArrayList<D>();

        for (D deck : decks) {
            decksList.add(deck);
        }
        this.decks = decksList;
    }

    /**
     * Returns the list of decks managed by this instance.
     *
     * @return The list of decks.
     */
    public List<D> getDecks() {
        return this.decks;
    }

    /**
     * Returns the number of decks managed by this instance.
     *
     * @return The number of decks.
     */
    public int size() {
        return this.decks.size();
    }

    /**
     * Returns the deck at the specified index.
     *
     * @param index The index of the deck to return.
     * @return The deck at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public D getDeck(int index) {
        return this.decks.get(index);
    }

    /**
     * Sets the deck at the specified index.
     *
     * @param index The index at which to set the deck.
     * @param deck  The deck to set at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void setDeck(int index, D deck) {
        assert index >= 0 && index < this.decks.size();
        this.decks.set(index, deck);
    }
}
