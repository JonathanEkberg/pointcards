package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.List;

/**
 * The Hand class represents a player's hand in the game Point Salad.
 * It contains two lists of cards: one for vegetable cards and one for criteria
 * cards.
 */
public class Hand {
    private List<Card> veggies = new ArrayList<>();
    private List<Card> criterias = new ArrayList<>();

    /**
     * Gets the list of vegetable cards in the hand.
     *
     * @return a list of vegetable cards.
     */
    public List<Card> getVeggieCards() {
        return veggies;
    }

    /**
     * Gets the list of criteria cards in the hand.
     *
     * @return a list of criteria cards.
     */
    public List<Card> getCriteriaCards() {
        return criterias;
    }

    /**
     * Adds one or more vegetable cards to the hand.
     *
     * @param card one or more vegetable cards to add.
     */
    public void addVeggieCard(Card... card) {
        this.veggies.addAll(List.of(card));
    }

    /**
     * Adds one or more criteria cards to the hand.
     *
     * @param card one or more criteria cards to add.
     */
    public void addCriteriasCard(Card... card) {
        this.criterias.addAll(List.of(card));
    }

    /**
     * Removes a vegetable card from the hand.
     *
     * @param card the vegetable card to remove.
     */
    public void removeVeggieCard(Card card) {
        this.veggies.remove(card);
    }

    /**
     * Removes a criteria card from the hand.
     *
     * @param card the criteria card to remove.
     */
    public void removeCriteriaCard(Card card) {
        this.criterias.remove(card);
    }

    /**
     * Calculates the score of the hand based on the criteria cards and the
     * opponents' vegetable cards.
     *
     * @param opponentsVeggies a list of lists of the opponents' vegetable cards.
     * @return the calculated score.
     */
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
