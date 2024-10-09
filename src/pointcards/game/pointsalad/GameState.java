package pointcards.game.pointsalad;

import java.util.List;

import pointcards.game.Bot;
import pointcards.game.Player;

public class GameState {
    public final List<Player> players;

    public GameState(List<Player> players) {
        this.players = players;
    }

    public GameState(List<Player> players, List<Bot> bots) {
        this.players = players;
    }
}
