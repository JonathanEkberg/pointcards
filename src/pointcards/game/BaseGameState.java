package pointcards.game;

import java.util.ArrayList;
import java.util.List;

import pointcards.utils.Randomizer;

public class BaseGameState {
    private final List<? extends BasePlayer> players;
    public final TurnController turner;

    public BaseGameState(List<? extends BasePlayer> players, List<? extends BaseBot> bots) {
        List<Participant> entities = new ArrayList<Participant>();
        entities.addAll(players);
        entities.addAll(bots);
        Randomizer.shuffle(entities);

        this.players = players;
        this.turner = new TurnController(entities);
    }

    public void sendMessageToAllPlayers(String message) {
        for (BasePlayer player : this.players) {
            player.getOutput().send(message);
        }
    }

    public void sendMessageToOtherPlayers(BasePlayer player, String message) {
        for (BasePlayer p : this.players) {
            if (player.equals(p)) {
                continue;
            }

            p.getOutput().send(message);
        }
    }
}
