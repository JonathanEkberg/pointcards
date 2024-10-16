package pointcards.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseGameState {
    private final List<? extends BasePlayer> players;
    public final TurnController turner;

    public BaseGameState(List<? extends BasePlayer> players, List<? extends BaseBot> bots) {
        List<Participant> entities = new ArrayList<Participant>();
        entities.addAll(players);
        entities.addAll(bots);
        Collections.shuffle(entities);

        this.players = players;
        this.turner = new TurnController(entities);
    }

    public void sendMessageToAllPlayers(String message) {
        for (BasePlayer player : this.players) {
            player.getOutput().send(message);
        }
    }
}