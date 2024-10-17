package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.List;

import pointcards.game.BaseBot;

public class Bot extends BaseBot implements IPlayer {
    private final Hand hand;

    public Bot(String name, Hand hand) {
        super(name);
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

}
