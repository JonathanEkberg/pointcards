package pointcards.game.pointsalad.phases;

import java.util.Optional;

import pointcards.game.Entity;
import pointcards.game.IPhase;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.PSPlayer;
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
        try {
            PSPlayer player = (PSPlayer) state.turner.getTurn();

            printPlayerTurn(player);
            handlePlayerTurn(player);
            // var input = player.getInput();

            // output.send("Welcome to Point Salad!");
            // Thread.sleep(500);
            // Logger.debug("Awaiting user input");
            // var result = input.queryString(
            // "Take either one point card (Syntax example: 2) or up to two vegetable cards
            // (Syntax example: CF):");
            // Logger.debug("User input '" + result + "'");
            // output.send("You entered: " + result);
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // if ()
        state.turner.next();
        Entity nextPlayer = state.turner.getTurn();

        if (nextPlayer instanceof PSPlayer) {
            return Optional.of(new PlayerTurnPhase(state));
        }

        return Optional.of(new BotTurnPhase(state));
    }

    private void printPlayerTurn(PSPlayer player) {
        IOutput output = player.getOutput();
        output.send(
                String.format(
                        "It's your turn!\n\nCriteria: %s\nVeggies: %s\n\nThe piles are:\nPoint cards: %s\nVeggie cards: %s\n",
                        "TODO", "TODO", "TODO", "TODO"));
    }

    private void handlePlayerTurn(PSPlayer player) {
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

    private void handleTakePointCard(PSPlayer player, int deckIdx) {
        // TODO
    }

    private void handleTakeMarketCards(PSPlayer player, char[] choices) {
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
