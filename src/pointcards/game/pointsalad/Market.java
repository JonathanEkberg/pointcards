package pointcards.game.pointsalad;

public class Market {
    public final Card[][] cardColumns = new Card[3][2];

    public class ColumnFullException extends Exception {
        public ColumnFullException(String message) {
            super(message);
        }
    }

    public void addCard(int columnIdx, Card card) throws ColumnFullException {
        if (this.cardColumns[columnIdx][1] != null) {
            throw new ColumnFullException("Column at index " + columnIdx + " is already full.");
        }

        if (this.cardColumns[columnIdx][0] != null) {
            this.cardColumns[columnIdx][1] = card;
            return;
        }

        this.cardColumns[columnIdx][0] = card;
    }

    public Card takeCard(int columnIdx, int rowIdx) {
        assert columnIdx >= 0 && columnIdx < 3 : "Column index out of bounds";
        assert rowIdx >= 0 && rowIdx < 2 : "Row index out of bounds";

        Card card = this.cardColumns[columnIdx][rowIdx];
        this.cardColumns[columnIdx][rowIdx] = null;
        return card;
    }
}
