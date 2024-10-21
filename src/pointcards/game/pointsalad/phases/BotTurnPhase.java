package pointcards.game.pointsalad.phases;

import java.util.Optional;

import pointcards.game.pointsalad.Bot;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.HumanPlayer;
import pointcards.utils.Logger;
import pointcards.game.IPhase;
import pointcards.game.Participant;

/**
 * The BotTurnPhase class represents the phase where a bot player takes their
 * turn.
 * It handles the bot's actions, updates the game state, and transitions to the
 * next phase.
 */
public class BotTurnPhase implements IPhase {
    private final GameState state;

    /**
     * Constructs a BotTurnPhase with the specified game state.
     * 
     * @param state The game state for the bot's turn.
     */
    public BotTurnPhase(GameState state) {
        this.state = state;
    }

    /**
     * Runs the bot's turn phase, handling their actions and updating the game
     * state.
     * 
     * @return The next phase to transition to, which could be a PlayerTurnPhase or
     *         another BotTurnPhase.
     */
    @Override
    public Optional<IPhase> run() {
        Bot bot = (Bot) state.turner.getTurn();

        String message = String.format("Bot %s is playing\n", bot.getName());
        Logger.debug(message);
        state.sendMessageToAllPlayers(message);
        bot.doTurn(state);
        Logger.debug(String.format("Bot %s finished turn\n", bot.getName()));

        if (state.shouldGameEnd()) {
            Logger.info("All decks are empty, finishing game");
            return Optional.of(new FinishPhase(state));
        }

        message = String.format("Bot %s's hand is:\n%s\n", bot.getName(),
                state.getPrinter().getPlayerHand(bot));
        state.sendMessageToAllPlayers(message);

        state.turner.next();
        Participant nextPlayer = state.turner.getTurn();

        if (nextPlayer instanceof HumanPlayer) {
            return Optional.of(new PlayerTurnPhase(state));
        }

        return Optional.of(new BotTurnPhase(state));

    }
}
