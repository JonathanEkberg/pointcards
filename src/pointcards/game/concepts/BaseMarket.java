package pointcards.game.concepts;

import java.lang.reflect.Array;

/**
 * The BaseMarket class represents a grid-based market for holding and managing
 * cards.
 * 
 * @param <T> The type of card that extends BaseCard.
 */
public class BaseMarket<T extends BaseCard> {
    private final int columns;
    private final int rows;
    private final T[][] matrix;

    /**
     * Constructs a BaseMarket with the specified number of columns and rows.
     * 
     * @param cardClass The class type of the card.
     * @param columns   The number of columns in the market.
     * @param rows      The number of rows in the market.
     * @throws IllegalArgumentException if columns or rows are less than or equal to
     *                                  0.
     */
    @SuppressWarnings("unchecked")
    public BaseMarket(Class<T> cardClass, int columns, int rows) {
        assert columns > 0 : "Columns must be greater than 0";
        assert rows > 0 : "Rows must be greater than 0";

        this.columns = columns;
        this.rows = rows;
        this.matrix = (T[][]) Array.newInstance(cardClass, columns, rows);
    }

    /**
     * Returns the number of columns in the market.
     * 
     * @return The number of columns.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Returns the number of rows in the market.
     * 
     * @return The number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Asserts that the given column and row indices are within bounds.
     * 
     * @param columnIdx The column index.
     * @param rowIdx    The row index.
     * @throws IndexOutOfBoundsException if the indices are out of bounds.
     */
    private void assertIndices(int columnIdx, int rowIdx) {
        assert columnIdx >= 0 && columnIdx < columns : "Column index out of bounds";
        assert rowIdx >= 0 && rowIdx < rows : "Row index out of bounds";
    }

    /**
     * Adds a card to the specified position in the market.
     * 
     * @param columnIdx The column index.
     * @param rowIdx    The row index.
     * @param card      The card to add.
     * @throws IndexOutOfBoundsException if the indices are out of bounds.
     */
    public void addCard(int columnIdx, int rowIdx, T card) {
        assertIndices(columnIdx, rowIdx);
        matrix[columnIdx][rowIdx] = card;
    }

    /**
     * Checks if there is a card at the specified position in the market.
     * 
     * @param columnIdx The column index.
     * @param rowIdx    The row index.
     * @return true if there is a card at the specified position, false otherwise.
     * @throws IndexOutOfBoundsException if the indices are out of bounds.
     */
    public boolean hasCard(int columnIdx, int rowIdx) {
        assertIndices(columnIdx, rowIdx);
        return matrix[columnIdx][rowIdx] != null;
    }

    /**
     * Retrieves the card at the specified position in the market.
     * 
     * @param columnIdx The column index.
     * @param rowIdx    The row index.
     * @return The card at the specified position, or null if there is no card.
     * @throws IndexOutOfBoundsException if the indices are out of bounds.
     */
    public T getCard(int columnIdx, int rowIdx) {
        assertIndices(columnIdx, rowIdx);
        T card = matrix[columnIdx][rowIdx];
        return card;
    }

    /**
     * Removes the card at the specified position in the market.
     * 
     * @param columnIdx The column index.
     * @param rowIdx    The row index.
     * @throws IndexOutOfBoundsException if the indices are out of bounds.
     */
    public void removeCard(int columnIdx, int rowIdx) {
        assertIndices(columnIdx, rowIdx);
        matrix[columnIdx][rowIdx] = null;
    }

    /**
     * Takes the card at the specified position in the market, removing it from the
     * market.
     * 
     * @param columnIdx The column index.
     * @param rowIdx    The row index.
     * @return The card that was taken, or null if there was no card.
     * @throws IndexOutOfBoundsException if the indices are out of bounds.
     */
    public T takeCard(int columnIdx, int rowIdx) {
        assertIndices(columnIdx, rowIdx);
        T card = getCard(columnIdx, rowIdx);
        removeCard(columnIdx, rowIdx);
        return card;
    }
}
