package pointcards.game.pointsalad;

import java.util.List;

import pointcards.game.concepts.BaseDeck;

/**
 * The {@code Deck} class represents a deck of cards in the Point Salad game.
 * It extends the {@link pointcards.game.concepts.BaseDeck} class, providing
 * additional functionalities specific to the Point Salad game.
 * 
 * <p>
 * This class provides constructors to create an empty deck or initialize it
 * with a list of cards.
 * </p>
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * {@code
 * // Create an empty deck
 * Deck emptyDeck = new Deck();
 * 
 * // Create a deck with a list of cards
 * List<Card> cards = Arrays.asList(new Card(...), new Card(...));
 * Deck deckWithCards = new Deck(cards);
 * }
 * </pre>
 * 
 * @see pointcards.game.concepts.BaseDeck
 */
public class Deck extends BaseDeck<Card> {
    /**
     * Constructs an empty {@code Deck}.
     */
    public Deck() {
        super();
    }

    /**
     * Constructs a {@code Deck} with the specified list of cards.
     * 
     * @param decks the list of cards to initialize the deck with
     */
    public Deck(List<Card> decks) {
        super(decks);
    }
}
