package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> veggies = new ArrayList<>();
    private List<Card> criterias = new ArrayList<>();

    public List<Card> getVeggieCards() {
        return veggies;
    }

    public List<Card> getCriteriaCards() {
        return criterias;
    }

    public void addVeggieCard(Card... card) {
        this.veggies.addAll(List.of(card));
    }

    public void addCriteriasCard(Card... card) {
        this.criterias.addAll(List.of(card));
    }
}
