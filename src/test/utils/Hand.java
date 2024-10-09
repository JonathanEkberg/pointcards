package test.utils;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;
import pointcards.game.pointsalad.criteria.criterias.CriteriaMost;

public class Hand {
    public static Card[] createHand(Veggie type, int amount) {
        ICriteria anyCriteria = new CriteriaMost(type, amount);
        Card[] hand = new Card[amount];

        for (int i = 0; i < amount; i++) {
            hand[i] = new Card(type, anyCriteria);
        }

        return hand;
    }
}
