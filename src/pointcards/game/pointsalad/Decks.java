package pointcards.game.pointsalad;

import java.util.List;

public class Decks {
    private final Deck[] decks;

    public Decks(List<Deck> decks) {
        this.decks = new Deck[decks.size()];

        int i = 0;
        for (Deck deck : decks) {
            this.decks[i] = deck;
            i++;
        }
    }

    public Decks(Deck... decks) {
        this.decks = decks;
    }

    public Deck[] getDecks() {
        return this.decks;
    }

    public int size() {
        return this.decks.length;
    }

    public Deck getDeck(int index) {
        return this.decks[index];
    }

    public void setDeck(int index, Deck deck) {
        assert index >= 0 && index < this.decks.length;
        this.decks[index] = deck;
    }
}
