package pointcards.game.pointsalad.phases;

import java.util.Optional;

import pointcards.game.Participant;
import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.GameStatePrinter;
import pointcards.game.IPhase;
import pointcards.game.pointsalad.HumanPlayer;
import pointcards.game.pointsalad.Market;
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

        String message = String.format("Player %s is playing\n", player.getName());
        state.sendMessageToOtherPlayers(player, message);

        printTurnGameState(player);
        handlePlayerTurn(player);

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
            throw new RuntimeException(String.format("Invalid input: %s (size: %d)", choice, choice.length()));
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
        handleTakeMarketCards(player, chars);
    }

    private void handleTakePointCard(HumanPlayer player, int deckIdx) {
        state.getDecks().getDeck(deckIdx).takeCard().ifPresent(card -> {
            player.getHand().addCriteriasCard(card);
        });
    }

    private int[] charToMarketPosition(char choice) {
        switch (choice) {
            case 'a':
                return new int[] { 0, 0 };
            case 'b':
                return new int[] { 1, 0 };
            case 'c':
                return new int[] { 2, 0 };
            case 'd':
                return new int[] { 0, 1 };
            case 'e':
                return new int[] { 1, 1 };
            case 'f':
                return new int[] { 2, 1 };
            default:
                throw new RuntimeException("Invalid market card choice");
        }
    }

    private void handleTakeMarketCards(HumanPlayer player, char[] choices) {
        Market market = state.getMarket();

        for (char choice : choices) {
            int[] coords = charToMarketPosition(choice);

            int columnIdx = coords[0];
            int rowIdx = coords[1];

            if (!market.hasCard(columnIdx, rowIdx)) {
                throw new RuntimeException("No card at position " + choice);
            }

            Card card = market.takeCard(columnIdx, rowIdx);
            player.getHand().addVeggieCard(card);
        }

        state.refillMarketCards();
    }
}
