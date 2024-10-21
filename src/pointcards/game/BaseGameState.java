package pointcards.game;

import java.util.ArrayList;
import java.util.List;

import pointcards.utils.Randomizer;

/**
 * The BaseGameState class represents the state of the game, managing players
 * and bots,
 * and controlling the turn order.
 */
public class BaseGameState {
    private final List<? extends BasePlayer> players;
    public final TurnController turner;

    /**
     * Constructs a new BaseGameState with the specified players and bots.
     * The players and bots are combined into a single list of participants,
     * shuffled, and then used to initialize the turn controller.
     *
     * @param players the list of players participating in the game
     * @param bots    the list of bots participating in the game
     */
    public BaseGameState(List<? extends BasePlayer> players, List<? extends BaseBot> bots) {
        List<Participant> entities = new ArrayList<Participant>();
        entities.addAll(players);
        entities.addAll(bots);
        Randomizer.shuffle(entities);

        this.players = players;
        this.turner = new TurnController(entities);
    }

    /**
     * Sends a message to all players in the game.
     *
     * @param message the message to be sent to all players
     */
    public void sendMessageToAllPlayers(String message) {
        for (BasePlayer player : this.players) {
            player.getOutput().send(message);
        }
    }

    /**
     * Sends a message to all players except the specified player.
     *
     * @param player  the player to be excluded from receiving the message
     * @param message the message to be sent to the other players
     */
    public void sendMessageToOtherPlayers(BasePlayer player, String message) {
        for (BasePlayer p : this.players) {
            if (player.equals(p)) {
                continue;
            }

            p.getOutput().send(message);
        }
    }
}
