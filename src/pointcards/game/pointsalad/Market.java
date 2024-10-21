package pointcards.game.pointsalad;

import pointcards.game.concepts.BaseMarket;

/**
 * The {@code Market} class represents a market of cards in the Point Salad
 * game.
 * It extends the {@link BaseMarket} class and provides methods to add and take
 * cards
 * from the market.
 * 
 * <p>
 * The market is organized in a grid with a fixed number of columns and rows.
 * Cards can be added to the market in a specific column, and taken from the
 * market
 * from a specific column.
 * </p>
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * {@code
 * Market market = new Market();
 * Card card = new Card(...);
 * market.addCard(0, card); // Adds a card to the first column
 * Card takenCard = market.takeCard(0); // Takes a card from the first column
 * }
 * </pre>
 */
public class Market extends BaseMarket<Card> {
    /**
     * Constructs a new {@code Market} with a fixed number of columns and rows.
     * The market is initialized to hold cards in a 3x2 grid.
     */
    public Market() {
        super(Card.class, 3, 2);
    }

    /**
     * Adds a card to the market in the specified column.
     * The card is added to the first available row in the column.
     * 
     * @param columnIdx The index of the column to add the card to.
     * @param card      The card to add to the market.
     */
    public void addCard(int columnIdx, Card card) {
        for (int row = 0; row < getRows(); row++) {
            if (!hasCard(columnIdx, row)) {
                addCard(columnIdx, row, card);
                return;
            }
        }
    }

    /**
     * Takes a card from the market from the specified column.
     * The card is taken from the last available row in the column.
     * 
     * @param columnIdx The index of the column to take the card from.
     * @return The card taken from the market.
     * @throws IllegalStateException if there are no cards in the specified column.
     */
    public Card takeCard(int columnIdx) {
        for (int row = getRows() - 1; row >= 0; row--) {
            if (hasCard(columnIdx, row)) {
                return takeCard(columnIdx, row);
            }
        }

        throw new IllegalStateException("Tried to take card without checking if it exists first.");
    }

    /**
     * Returns the total number of cards in the market.
     * 
     * @return The total number of cards in the market.
     */
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
