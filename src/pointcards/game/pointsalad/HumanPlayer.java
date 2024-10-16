package pointcards.game.pointsalad;

import pointcards.game.BasePlayer;
import pointcards.io.input.IInput;
import pointcards.io.output.IOutput;

public class HumanPlayer extends BasePlayer implements IPlayer {
    private final Hand hand;

    public HumanPlayer(BasePlayer player, Hand hand) {
        super(player.getInput(), player.getOutput());
        this.hand = hand;
    }

    public HumanPlayer(IInput input, IOutput output, Hand hand) {
        super(input, output);
        this.hand = hand;
    }

    public String getName() {
        return String.valueOf(this.id);
    }

    public Hand getHand() {
        return hand;
    }
}
