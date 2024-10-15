package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.List;

import pointcards.game.Bot;

public class PSBot extends Bot {
    private ArrayList<Card> hand;

    public PSBot() {
        this.hand = new ArrayList<>();
    }

    public void addCard(Card... card) {
        this.hand.addAll(List.of(card));
    }
}
