package pointcards.game.criteria;

import pointcards.game.pointsalad.Card;

public interface ICriteria {
    public int calculatePoints(Card[] owner, Card[][] opponents);

    public String toString();
}
