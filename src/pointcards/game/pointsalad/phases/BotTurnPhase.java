package pointcards.game.pointsalad.phases;

import java.util.Optional;

import pointcards.game.pointsalad.Bot;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.HumanPlayer;
import pointcards.utils.Logger;
import pointcards.game.IPhase;
import pointcards.game.Participant;

public class BotTurnPhase implements IPhase {
    private final GameState state;

    public BotTurnPhase(GameState state) {
        this.state = state;
    }

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
