package pointcards.game.pointsalad;

import pointcards.game.BasePlayer;
import pointcards.io.input.IInput;
import pointcards.io.output.IOutput;
import pointcards.utils.Logger;

public class HumanPlayer extends BasePlayer implements IPlayer {
    private final Hand hand;

    public HumanPlayer(BasePlayer player, Hand hand) {
        super(player.getName(), player.getInput(), player.getOutput());
        this.hand = hand;
    }

    public HumanPlayer(String name, IInput input, IOutput output, Hand hand) {
        super(name, input, output);
        this.hand = hand;
    }

    // public String getName() {
    // return String.valueOf(this.getName());
    // }

    public Hand getHand() {
        return hand;
    }
}
