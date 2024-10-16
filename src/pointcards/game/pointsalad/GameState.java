package pointcards.game.pointsalad;

import java.util.List;

import pointcards.game.BaseGameState;

public class GameState extends BaseGameState {
    private Decks decks;
    private Market market;
    private GameStatePrinter printer;

    public GameState(List<Player> players, List<Bot> bots, Deck deck) {
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
}
