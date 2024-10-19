package pointcards.game.pointsalad;

import pointcards.game.concepts.BaseMarket;

public class Market extends BaseMarket<Card> {
    public Market() {
        super(Card.class, 3, 2);
    }

    public void addCard(int columnIdx, Card card) {
        for (int row = 0; row < getRows(); row++) {
            if (!hasCard(columnIdx, row)) {
                addCard(columnIdx, row, card);
                return;
            }
        }
    }

    public Card takeCard(int columnIdx) {
        for (int row = getRows() - 1; row >= 0; row--) {
            if (hasCard(columnIdx, row)) {
                return takeCard(columnIdx, row);
            }
        }

        throw new IllegalStateException("Tried to take card without checking if it exists first.");
    }

    public int size() {
        int size = 0;

        for (int column = 0; column < getColumns(); column++) {
            for (int row = 0; row < getRows(); row++) {
                if (hasCard(column, row)) {
                    size++;
                }
            }
        }

        return size;
    }
}
