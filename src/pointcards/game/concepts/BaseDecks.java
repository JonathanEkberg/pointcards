package pointcards.game.concepts;

import java.util.ArrayList;
import java.util.List;

public class BaseDecks<D extends BaseDeck<C>, C extends BaseCard> {
    private final List<D> decks;

    public BaseDecks(List<D> decks) {
        List<D> decksList = new ArrayList<D>();

        for (D deck : decks) {
            decksList.add(deck);
        }
        this.decks = decksList;
    }

    public List<D> getDecks() {
        return this.decks;
    }

    public int size() {
        return this.decks.size();
    }

    public D getDeck(int index) {
        return this.decks.get(index);
    }

    public void setDeck(int index, D deck) {
        assert index >= 0 && index < this.decks.size();
        this.decks.set(index, deck);
    }
}
