package pointcards.game.pointsalad.criteria;

import pointcards.game.pointsalad.Card;

public interface ICriteria {
    public int calculatePoints(Card[] owner, Card[][] opponents);
}
