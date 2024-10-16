package pointcards.game.pointsalad;

import java.util.List;

import pointcards.game.concepts.BaseDeck;

public class Deck extends BaseDeck<Card> {
    public Deck() {
        super();
    }

    public Deck(List<Card> decks) {
        super(decks);
    }
}
