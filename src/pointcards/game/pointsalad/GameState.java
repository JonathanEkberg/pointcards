package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.List;
import pointcards.game.BaseGameState;
import pointcards.game.pointsalad.concepts.Card;
import pointcards.game.pointsalad.concepts.Deck;
import pointcards.game.pointsalad.concepts.Decks;
import pointcards.game.pointsalad.concepts.Market;
import pointcards.game.pointsalad.participants.Bot;
import pointcards.game.pointsalad.participants.HumanPlayer;
import pointcards.game.pointsalad.participants.IPlayer;
import pointcards.utils.Logger;

/**
 * The {@code GameState} class represents the current state of the Point Salad
 * game.
 * It manages the decks, market, and other game components, providing methods to
 * manipulate and query the game state.
 * 
 * <p>
 * This class includes methods for:
 * <ul>
 * <li>Getting and setting the decks and market</li>
 * <li>Refilling the market cards</li>
 * <li>Printing the game state</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * {@code
 * GameState state = new GameState(decks, market);
 * state.refillMarketCards();
 * }
 * </pre>
 * 
 * @see pointcards.game.pointsalad.concepts.Decks
 * @see pointcards.game.pointsalad.concepts.Market
 * @see pointcards.game.pointsalad.GameStatePrinter
 */
public class GameState extends BaseGameState {
    private Decks decks;
    private Market market;
    private GameStatePrinter printer;
    private final List<IPlayer> players;

    public GameState(List<HumanPlayer> players, List<Bot> bots, Deck deck) {
        super(players, bots);
        this.decks = new Decks(List.of(deck));
        this.market = new Market();
        this.printer = new GameStatePrinter(this);

        this.players = new ArrayList<>(players.size() + bots.size());
        for (HumanPlayer player : players) {
            this.players.add(player);
        }
        for (Bot bot : bots) {
            this.players.add(bot);
        }
    }

    public List<IPlayer> getPlayers() {
        return this.players;
    }

    /**
     * Gets the decks in the game state.
     * 
     * @return The decks.
     */
    public Decks getDecks() {
        return this.decks;
    }

    /**
     * Gets the market in the game state.
     * 
     * @return The market.
     */
    public Market getMarket() {
        return this.market;
    }

    /**
     * Gets the game state printer.
     * 
     * @return The game state printer.
     */
    public GameStatePrinter getPrinter() {
        return this.printer;
    }

    /**
     * Sets the decks in the game state.
     * 
     * @param decks The decks to set.
     */
    public void setDecks(Decks decks) {
        this.decks = decks;
    }

    /**
     * Refills the market cards if there are empty slots.
     * If there are decks with zero cards, it refills with empty cards.
     * Otherwise, it refills with non-empty cards.
     */
    public void refillMarketCards() {
        boolean hasEmptyMarketSlot = false;
        for (int column = 0; column < market.getColumns(); column++) {
            for (int row = 0; row < market.getRows(); row++) {
                if (!market.hasCard(column, row)) {
                    hasEmptyMarketSlot = true;
                    break;
                }
            }
        }

        if (!hasEmptyMarketSlot) {
            return;
        }

        Decks decks = getDecks();
        boolean existsDeckWithZeroCards = false;

        for (Deck deck : decks.getDecks()) {
            if (deck.size() == 0) {
                existsDeckWithZeroCards = true;
                break;
            }
        }

        if (existsDeckWithZeroCards) {
            refillWithEmpty();
        } else {
            refillWithNonEmpty();
        }
    }

    /**
     * Refills the market with non-empty cards from the decks.
     */
    private void refillWithNonEmpty() {
        for (int i = 0; i < 6; i++) {
            int columnIndex = i % 3;
            int rowIndex = i % 2;

            if (market.hasCard(columnIndex, rowIndex)) {
                continue;
            }

            Deck deck = decks.getDeck(columnIndex);
            Card card = deck.takeCard().get();
            market.addCard(columnIndex, rowIndex, card);
        }
    }

    /**
     * Refills the market with empty cards.
     */
    private void refillWithEmpty() {
        Market market = getMarket();

        for (int column = 0; column < market.getColumns(); column++) {
            for (int row = 0; row < market.getRows(); row++) {
                if (market.hasCard(column, row)) {
                    continue;
                }

                Logger.debug("Refilling market spot at " + column + ", " + row);
                refillMarketSpot(column, row);
            }
        }
    }

    private void refillMarketSpot(int column, int row) {
        Deck deck = decks.getDeck(column);

        if (deck.size() != 0) {
            Card card = deck.takeCard().get();
            market.addCard(column, row, card);
            return;
        }

        // Refill market spot with card from bottom of the deck with the most cards
        int columnWithMostCards = -1;

        for (int i = 0; i < decks.size(); i++) {
            // This column is empty, skip it
            if (i == column) {
                continue;
            }

            if (columnWithMostCards == -1) {
                columnWithMostCards = i;
                continue;
            }

            if (decks.getDeck(i).size() > decks.getDeck(columnWithMostCards).size()) {
                columnWithMostCards = i;
            }
        }

        for (int i = 0; i < decks.size(); i++) {
            Logger.debug("");
            Logger.debug("Deck " + i + " has " + decks.getDeck(i).size() + " cards");
        }

        deck = decks.getDeck(columnWithMostCards);
        assert deck.size() > 0 : "Deck with most cards is empty";

        Card card = deck.takeFromBottom().get();
        market.addCard(column, row, card);
    }

    /**
     * Checks if all decks are empty.
     *
     * @return true if all decks are empty, false otherwise.
     */
    public boolean allDecksEmpty() {
        for (Deck deck : decks.getDecks()) {
            if (deck.size() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the market is completely empty.
     *
     * @return true if the market is completely empty, false otherwise.
     */
    public boolean marketCompletelyEmpty() {
        for (int column = 0; column < market.getColumns(); column++) {
            for (int row = 0; row < market.getRows(); row++) {
                if (market.hasCard(column, row)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determines if the game should end.
     *
     * @return true if all decks are empty and the market is completely empty, false
     *         otherwise.
     */
    public boolean shouldGameEnd() {
        return allDecksEmpty() && marketCompletelyEmpty();
    }
}
