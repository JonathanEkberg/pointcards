package pointcards.game.concepts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseDeck<C extends BaseCard> {
    private final List<C> cards;

    public BaseDeck() {
        this.cards = new ArrayList<>();
    }

    public BaseDeck(List<C> cards) {
        this.cards = cards;
    }

    public List<C> getCards() {
        return this.cards;
    }

    public Optional<C> takeCard() {
        if (this.size() == 0) {
            return Optional.empty();
        }

        C card = this.cards.get(0);
        this.cards.remove(0);

        return Optional.of(card);
    }

    public int size() {
        return this.cards.size();
    }

    public void addCard(C card) {
        this.cards.add(card);
    }
}
