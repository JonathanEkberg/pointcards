package pointcards.game.pointsalad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pointcards.game.BaseGameState;

public class GameState extends BaseGameState {
    private Decks decks;
    private Market market;
    private GameStatePrinter printer;

    public GameState(List<HumanPlayer> players, List<Bot> bots, Deck deck) {
        super(players, bots);
        this.decks = new Decks(List.of(deck));
        this.market = new Market();
        this.printer = new GameStatePrinter(this);
    }

    public Decks getDecks() {
        return this.decks;
    }

    public Market getMarket() {
        return this.market;
    }

    public GameStatePrinter getPrinter() {
        return this.printer;
    }

    public void setDecks(Decks decks) {
        this.decks = decks;
    }

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

    private void refillWithEmpty() {
        Market market = getMarket();

        for (int column = 0; column < market.getColumns(); column++) {
            for (int row = 0; row < market.getRows(); row++) {
                if (market.hasCard(column, row)) {
                    continue;
                }

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
        Map<Integer, Integer> deckSizes = new HashMap<>();

        for (int i = 0; i < decks.size(); i++) {
            // This column is empty, skip it
            if (i == column) {
                continue;
            }

            deckSizes.put(i, decks.getDeck(i).size());
        }

        int columnWithMostCards = deckSizes.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        deck = decks.getDeck(columnWithMostCards);
        Card card = deck.takeFromBottom().get();
        market.addCard(column, row, card);
    }
}
