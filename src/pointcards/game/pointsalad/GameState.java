package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pointcards.game.Entity;
import pointcards.game.TurnController;

public class GameState {
    public final TurnController turner;
    private Decks decks;
    private Market market;

    public GameState(List<PSPlayer> players, List<PSBot> bots, Deck deck) {
        this.decks = new Decks(deck);
        this.market = new Market();

        List<Entity> entities = new ArrayList<Entity>();
        entities.addAll(players);
        entities.addAll(bots);
        Collections.shuffle(entities);

        this.turner = new TurnController(entities);
    }

    public Decks getDecks() {
        return this.decks;
    }

    public Market getMarket() {
        return this.market;
    }

    public void setDecks(Decks decks) {
        this.decks = decks;
    }
}
