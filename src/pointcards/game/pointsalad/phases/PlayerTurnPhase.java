package pointcards.game.pointsalad.phases;

import java.util.Optional;

import pointcards.game.Participant;
import pointcards.game.pointsalad.Deck;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.GameStatePrinter;
import pointcards.game.IPhase;
import pointcards.game.pointsalad.HumanPlayer;
import pointcards.io.output.IOutput;
import pointcards.utils.Logger;

public class PlayerTurnPhase implements IPhase {
    private final GameState state;

    public PlayerTurnPhase(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return this.state;
    }

    @Override
    public Optional<IPhase> run() {
        HumanPlayer player = (HumanPlayer) state.turner.getTurn();

        String message = String.format("Player %s is playing\n", player.getName());
        state.sendMessageToOtherPlayers(player, message);

        printTurnGameState(player);
        player.doTurn(state);

        boolean allDecksEmpty = true;
        for (Deck deck : state.getDecks().getDecks()) {
            if (deck.size() > 0) {
                allDecksEmpty = false;
                break;
            }
        }

        if (allDecksEmpty) {
            Logger.info("All decks are empty, finishing game");
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

    private void printTurnGameState(HumanPlayer player) {
        IOutput output = player.getOutput();
        GameStatePrinter printer = state.getPrinter();
        String[] lines = new String[] {
                "********************************",
                "It's your turn!",
                "",
                printer.getPlayerHand(player),
                // printer.getPlayerCriteriaCards(player),
                // "Veggies: TODO",
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
