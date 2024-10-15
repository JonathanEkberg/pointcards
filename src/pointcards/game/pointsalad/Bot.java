package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.List;

import pointcards.game.BaseBot;

public class Bot extends BaseBot {
    private ArrayList<Card> hand;

    public Bot() {
        this.hand = new ArrayList<>();
    }

    public void addCard(Card... card) {
        this.hand.addAll(List.of(card));
    }
}
