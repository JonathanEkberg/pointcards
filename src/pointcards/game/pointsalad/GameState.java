package pointcards.game.pointsalad;

import java.util.List;

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
        for (int i = 0; i < 6; i++) {
            int columnIndex = i % 3;
            int rowIndex = i % 2;

            if (market.hasCard(columnIndex, rowIndex)) {
                continue;
            }

            Deck deck = decks.getDeck(columnIndex);

            if (deck.size() == 0) {
                // TODO: Take cards from other rdecks?
                throw new RuntimeException("Unimplemented deck refill");
            }

            Card card = deck.takeCard().get();
            market.addCard(columnIndex, rowIndex, card);
        }
    }
}
