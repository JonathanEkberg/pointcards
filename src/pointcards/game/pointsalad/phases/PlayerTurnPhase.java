package pointcards.game.pointsalad.phases;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pointcards.game.Participant;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.GameStatePrinter;
import pointcards.game.IPhase;
import pointcards.game.pointsalad.Hand;
import pointcards.game.pointsalad.HumanPlayer;
import pointcards.io.input.IInput;
import pointcards.io.output.IOutput;
import pointcards.utils.Logger;

public class PlayerTurnPhase implements IPhase {
    private final GameState state;

    public PlayerTurnPhase(GameState state) {
        this.state = state;
    }

    @Override
    public Optional<IPhase> run() {
        HumanPlayer player = (HumanPlayer) state.turner.getTurn();

        printTurnGameState(player);
        handlePlayerTurn(player);

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
                printer.getPlayerCriteriaCards(player),
                "Veggies: TODO",
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
        // output.send(
        // String.format(
        // "It's your turn!\n\nCriteria: %s\nVeggies: %s\n\nThe piles are:\nPoint cards:
        // %s\nVeggie cards: %s\n",
        // "TODO", "TODO", "TODO", "TODO"));
    }

    private void handlePlayerTurn(HumanPlayer player) {
        IInput input = player.getInput();
        String choice = input.queryString(
                "Take either one point card (Syntax example: 2) or up to two vegetable cards (Syntax example: CF)");
        try {
            int parsed = Integer.parseInt(choice);
            handleTakePointCard(player, parsed);
            return;
        } catch (NumberFormatException e) {
            // Logger.debug("User input '" + choice + "' " + choice.length());
            // if (choice.length() != 2) {
            // Logger.debug("User input '" + choice + "' " + choice.length());
            // }
        }

        if (choice.length() != 2) {
            // TODO: Handle invalid input
            throw new RuntimeException("Invalid input");
        }

        char[] chars = choice.toLowerCase().toCharArray();
        // Check that chars are all a-f
        StringBuilder sb = new StringBuilder(2);
        for (char c : chars) {
            if (c < 'a' || c > 'f') {
                throw new RuntimeException("Bad character choice");
            }
            sb.append(c);
        }

        Logger.debug("User input: " + sb.toString());
    }

    private void handleTakePointCard(HumanPlayer player, int deckIdx) {
        state.getDecks().getDeck(deckIdx).takeCard().ifPresent(card -> {
            player.getHand().addCriteriasCard(card);
        });
    }

    private void handleTakeMarketCards(HumanPlayer player, char[] choices) {
        // TODO
        // Map chars to column and row indices
        int columnIdx = -1;
        int rowIdx = -1;

        switch (choices[0]) {
            case 'a':
                columnIdx = 0;
                break;
            case 'b':
                columnIdx = 1;
                break;
            case 'c':
                columnIdx = 2;
                break;
            default:
                throw new RuntimeException("Invalid column choice");
        }
    }
}
