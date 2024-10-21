package pointcards.game.concepts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pointcards.utils.Randomizer;

/**
 * The BaseDeck class represents a deck of cards.
 * 
 * @param <C> the type of card that this deck holds, which extends BaseCard
 */
public class BaseDeck<C extends BaseCard> {
    private final List<C> cards;

    /**
     * Constructs an empty deck of cards.
     */
    public BaseDeck() {
        this.cards = new ArrayList<>();
    }

    /**
     * Constructs a deck of cards with the given list of cards.
     * 
     * @param cards the list of cards to initialize the deck with
     */
    public BaseDeck(List<C> cards) {
        this.cards = cards;
    }

    /**
     * Returns the list of cards in the deck.
     * 
     * @return the list of cards in the deck
     */
    public List<C> getCards() {
        return this.cards;
    }

    /**
     * Takes a card from the top of the deck.
     * 
     * @return an Optional containing the card if the deck is not empty, otherwise
     *         an empty Optional
     */
    public Optional<C> takeCard() {
        if (this.size() == 0) {
            return Optional.empty();
        }

        C card = this.cards.get(0);
        this.cards.remove(0);

        return Optional.of(card);
    }

    /**
     * Takes a card from the bottom of the deck.
     * 
     * @return an Optional containing the card if the deck is not empty, otherwise
     *         an empty Optional
     */
    public Optional<C> takeFromBottom() {
        if (this.size() == 0) {
            return Optional.empty();
        }

        C card = this.cards.get(this.size() - 1);
        this.cards.remove(this.size() - 1);

        return Optional.of(card);
    }

    /**
     * Shuffles the deck of cards.
     */
    public void shuffle() {
        Randomizer.shuffle(this.cards);
    }

    /**
     * Returns the number of cards in the deck.
     * 
     * @return the number of cards in the deck
     */
    public int size() {
        return this.cards.size();
    }

    /**
     * Adds a card to the deck.
     * 
     * @param card the card to add to the deck
     */
    public void addCard(C card) {
        this.cards.add(card);
    }
}
