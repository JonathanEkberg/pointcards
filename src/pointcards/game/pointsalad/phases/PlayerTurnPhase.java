package pointcards.game.pointsalad.phases;

import java.util.Optional;

import pointcards.game.Participant;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.GameStatePrinter;
import pointcards.game.IPhase;
import pointcards.game.pointsalad.concepts.Deck;
import pointcards.game.pointsalad.participants.HumanPlayer;
import pointcards.io.output.IOutput;
import pointcards.utils.Logger;

/**
 * The PlayerTurnPhase class represents the phase where a human player takes
 * their turn.
 * It handles the player's actions, updates the game state, and transitions to
 * the next phase.
 */
public class PlayerTurnPhase implements IPhase {
    private final GameState state;

    /**
     * Constructs a PlayerTurnPhase with the specified game state.
     * 
     * @param state The game state for the player's turn.
     */
    public PlayerTurnPhase(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return this.state;
    }

    /**
     * Runs the player's turn phase, handling their actions and updating the game
     * state.
     * 
     * @return The next phase to transition to, which could be a BotTurnPhase or
     *         another PlayerTurnPhase.
     */
    @Override
    public Optional<IPhase> run() {
        HumanPlayer player = (HumanPlayer) state.turner.getTurn();

        String message = String.format("********************************\nPlayer %s is playing\n", player.getName());
        state.sendMessageToOtherPlayers(player, message);

        printTurnGameState(player);
        player.doTurn(state);

        if (state.shouldGameEnd()) {
            Logger.info("Decks and market empty. Finishing game.");
            return Optional.of(new FinishPhase(state));
        }

        message = String.format("Player %s's hand is:\n%s\n", player.getName(),
                state.getPrinter().getPlayerHand(player));
        state.sendMessageToOtherPlayers(player, message);

        state.turner.next();
        Participant nextPlayer = state.turner.getTurn();

        if (nextPlayer instanceof HumanPlayer) {
            return Optional.of(new PlayerTurnPhase(state));
        }

        return Optional.of(new BotTurnPhase(state));
    }

    /**
     * Prints the game state for the player's turn.
     * 
     * @param player The player whose turn it is.
     */
    private void printTurnGameState(HumanPlayer player) {
        IOutput output = player.getOutput();
        GameStatePrinter printer = state.getPrinter();
        String[] lines = new String[] {
                "********************************",
                "It's your turn!",
                "",
                printer.getPlayerHand(player),
                "",
                "The piles are:",
                printer.getPointCardChoics(),
                printer.getVeggieCardChoics(),
                "",
                ""
        };
        for (String line : lines) {
            output.send(line);
        }
    }

}
