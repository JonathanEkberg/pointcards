package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.List;

import pointcards.game.BaseBot;

public class Bot extends BaseBot implements IPlayer {
    private final Hand hand;

    public Bot(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return String.valueOf(id);
    }
}
