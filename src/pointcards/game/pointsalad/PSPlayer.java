package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.List;

import pointcards.game.Player;
import pointcards.io.input.IInput;
import pointcards.io.output.IOutput;

public class PSPlayer extends Player {
    private ArrayList<Card> hand;

    public PSPlayer(IInput input, IOutput output) {
        super(input, output);
        this.hand = new ArrayList<>();
    }

    public void addCard(Card... card) {
        this.hand.addAll(List.of(card));
    }
}
