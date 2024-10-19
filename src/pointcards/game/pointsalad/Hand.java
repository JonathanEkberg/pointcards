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

    public void removeVeggieCard(Card card) {
        this.veggies.remove(card);
    }

    public void removeCriteriaCard(Card card) {
        this.criterias.remove(card);
    }

    public int calculateScore(List<List<Card>> opponentsVeggies) {
        int score = 0;

        for (Card card : criterias) {
            Card[] veggies = this.veggies.toArray(new Card[0]);
            Card[][] opponentsVeggiesArray = new Card[opponentsVeggies.size()][];
            for (int i = 0; i < opponentsVeggies.size(); i++) {
                opponentsVeggiesArray[i] = opponentsVeggies.get(i).toArray(new Card[0]);
            }
            score += card.getCriteria().calculatePoints(veggies, (opponentsVeggiesArray));
        }

        return score;
    }
}
