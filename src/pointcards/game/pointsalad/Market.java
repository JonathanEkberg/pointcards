package pointcards.game.pointsalad;

import pointcards.game.concepts.BaseMarket;

public class Market extends BaseMarket<Card> {
    public Market() {
        super(Card.class, 3, 2);
    }

    public void addCard(int columnIdx, Card card) {
        int rowIdx = 0;
        while (rowIdx < getRows() && getCard(columnIdx, rowIdx) != null) {
            rowIdx++;
        }

        if (rowIdx < getRows()) {
            addCard(columnIdx, rowIdx, card);
        }
    }

    public Card takeCard(int columnIdx) {
        int rowIdx = 0;
        while (rowIdx < getRows() && getCard(columnIdx, rowIdx) == null) {
            rowIdx++;
        }

        if (rowIdx < getRows()) {
            Card card = getCard(columnIdx, rowIdx);
            removeCard(columnIdx, rowIdx);
            return card;
        }

        return null;
    }
}
