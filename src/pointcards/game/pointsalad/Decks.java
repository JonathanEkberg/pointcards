package pointcards.game.pointsalad;

import java.util.List;

import pointcards.game.concepts.BaseDecks;

public class Decks extends BaseDecks<Deck, Card> {
    public Decks(List<Deck> decks) {
        super(decks);
    }

    public boolean allEmptyDecks() {
        for (Deck deck : this.getDecks()) {
            if (deck.size() != 0) {
                return false;
            }
        }

        return true;
    }
}
