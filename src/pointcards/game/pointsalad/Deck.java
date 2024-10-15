package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public Optional<Card> takeCard() {
        if (this.size() == 0) {
            return Optional.empty();
        }

        Card card = this.cards.get(0);
        this.cards.remove(0);

        return Optional.of(card);
    }

    public int size() {
        return this.cards.size();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }
}
