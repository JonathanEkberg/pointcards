package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.List;

import pointcards.game.Player;
import pointcards.io.input.IInput;
import pointcards.io.output.IOutput;

public class PSPlayer extends Player {
    private List<Card> veggies = new ArrayList<>();
    private List<Card> criterias = new ArrayList<>();

    public PSPlayer(Player player) {
        super(player.getInput(), player.getOutput());
    }

    public PSPlayer(IInput input, IOutput output) {
        super(input, output);
    }

    public List<Card> getVeggies() {
        return veggies;
    }

    public List<Card> getCriterias() {
        return criterias;
    }

    public void addVeggie(Card... card) {
        this.veggies.addAll(List.of(card));
    }

    public void addCriterias(Card... card) {
        this.criterias.addAll(List.of(card));
    }
}
