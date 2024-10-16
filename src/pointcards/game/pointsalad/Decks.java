package pointcards.game.pointsalad;

import java.util.List;

import pointcards.game.concepts.BaseDecks;

public class Decks extends BaseDecks<Card, Deck> {
    public Decks(List<Deck> decks) {
        super(decks);
    }
}
