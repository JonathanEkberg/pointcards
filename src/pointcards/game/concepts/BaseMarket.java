package pointcards.game.concepts;

import java.lang.reflect.Array;

public class BaseMarket<T extends BaseCard> {
    private final int columns;
    private final int rows;
    private final T[][] matrix;

    @SuppressWarnings("unchecked")
    public BaseMarket(Class<T> cardClass, int columns, int rows) {
        assert columns > 0 : "Columns must be greater than 0";
        assert rows > 0 : "Rows must be greater than 0";

        this.columns = columns;
        this.rows = rows;
        this.matrix = (T[][]) Array.newInstance(cardClass, columns, rows);

    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    private void assertIndices(int columnIdx, int rowIdx) {
        assert columnIdx >= 0 && columnIdx < columns : "Column index out of bounds";
        assert rowIdx >= 0 && rowIdx < rows : "Row index out of bounds";
    }

    public void addCard(int columnIdx, int rowIdx, T card) {
        assertIndices(columnIdx, rowIdx);
        matrix[columnIdx][rowIdx] = card;
    }

    public T getCard(int columnIdx, int rowIdx) {
        assertIndices(columnIdx, rowIdx);
        T card = matrix[columnIdx][rowIdx];
        return card;
    }

    public void removeCard(int columnIdx, int rowIdx) {
        assertIndices(columnIdx, rowIdx);
        matrix[columnIdx][rowIdx] = null;
    }

    public T takeCard(int columnIdx, int rowIdx) {
        assertIndices(columnIdx, rowIdx);
        T card = getCard(columnIdx, rowIdx);
        removeCard(columnIdx, rowIdx);
        return card;
    }
}
