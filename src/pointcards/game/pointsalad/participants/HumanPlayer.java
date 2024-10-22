package pointcards.game.pointsalad.participants;

import java.io.EOFException;

import javax.management.RuntimeErrorException;

import pointcards.GameServer;
import pointcards.game.BasePlayer;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.concepts.Card;
import pointcards.game.pointsalad.concepts.Hand;
import pointcards.game.pointsalad.concepts.Market;
import pointcards.io.input.IInput;
import pointcards.io.output.IOutput;
import pointcards.utils.Logger;

public class HumanPlayer extends BasePlayer implements IPlayer {
    private final Hand hand;

    public HumanPlayer(BasePlayer player, Hand hand) {
        super(player.getName(), player.getInput(), player.getOutput());
        this.hand = hand;
    }

    public HumanPlayer(String name, IInput input, IOutput output, Hand hand) {
        super(name, input, output);
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public void doTurn(GameState state) {
        IInput input = getInput();
        String choice = input.queryString(
                "Take either one point card (Syntax example: 2) or up to two vegetable cards (Syntax example: CF)");

        if (choice == null) {
            throw new GameServer.ExitGameException("User disconnected. Exiting the game.");
        }

        try {
            int parsed = Integer.parseInt(choice);

            if (parsed < 0 || parsed > 2) {
                getOutput()
                        .send("Invalid input. Please enter an integer between 0 and 2 if you want to take a point card.");
                this.doTurn(state);
                return;
            }

            if (state.getDecks().getDeck(parsed).size() == 0) {
                getOutput().send("No cards in deck " + parsed);
                this.doTurn(state);
                return;
            }

            handleTakePointCard(state, parsed);
            checkCriteriaCardConversion(state);
            return;
        } catch (NumberFormatException e) {
        }

        if (state.getMarket().size() > 1 && choice.length() != 2) {
            getOutput().send("Invalid input. Please enter exactly two characters.");
            this.doTurn(state);
            return;
        }

        if (state.getMarket().size() == 1 && choice.length() != 1) {
            getOutput().send("Invalid input. You must take the last item from the market.");
            this.doTurn(state);
            return;
        }

        char[] chars = choice.toLowerCase().toCharArray();
        // Check that chars are all a-f
        StringBuilder sb = new StringBuilder(2);
        for (char c : chars) {
            if (c < 'a' || c > 'f') {
                getOutput().send("Invalid input. Please two characters between A-F.");
                this.doTurn(state);
                return;
            }
            sb.append(c);
        }

        Logger.debug("User input: " + sb.toString());
        try {
            handleTakeMarketCards(state, chars);
        } catch (IllegalArgumentException e) {
            getOutput().send(e.getMessage());
            this.doTurn(state);
            return;
        }
        checkCriteriaCardConversion(state);
    }

    private void handleTakePointCard(GameState state, int deckIdx) {
        state.getDecks().getDeck(deckIdx).takeCard().ifPresent(card -> {
            hand.addCriteriasCard(card);
        });
    }

    private void checkCriteriaCardConversion(GameState state) {
        // If has zero critiera cards then they cannot convert to veggie
        if (hand.getCriteriaCards().size() == 0) {
            return;
        }

        IInput input = getInput();

        Boolean wantsToConvertPointCard = null;

        while (wantsToConvertPointCard == null) {
            String message = hand.getCriteriaCards().size() > 1
                    ? "Would you like to convert a criteria card to a veggie card? (y/n)"
                    : "Would you like to convert your criteria card to a veggie card? (y/n)";
            String answer = input.queryString(message).toLowerCase();

            if (!answer.equals("y") && !answer.equals("n")) {
                getOutput().send("Invalid input. Please enter 'y' or 'n'");
                continue;
            }

            wantsToConvertPointCard = answer.equals("y");
        }

        if (!wantsToConvertPointCard) {
            return;
        }

        IOutput output = getOutput();
        int criteriaCardIdx = 0;

        if (hand.getCriteriaCards().size() > 1) {
            output.send("\n" + state.getPrinter().getPlayerCriteriaCards(this));
            criteriaCardIdx = input.queryInt("Select criteria card (0 is first, 1 is second, etc)", 0,
                    hand.getCriteriaCards().size() - 1);
        }

        Card critieriaCard = hand.getCriteriaCards().get(criteriaCardIdx);
        hand.removeCriteriaCard(critieriaCard);
        hand.addVeggieCard(critieriaCard);
        output.send(
                String.format("Converted your %s card to a %s veggie card",
                        critieriaCard.getCriteria().toString(),
                        critieriaCard.getVeggie()));
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

    private void handleTakeMarketCards(GameState state, char[] choices) throws IllegalArgumentException {
        Market market = state.getMarket();

        // Check that all choices are valid
        for (char choice : choices) {
            int[] coords = charToMarketPosition(choice);

            int columnIdx = coords[0];
            int rowIdx = coords[1];

            if (!market.hasCard(columnIdx, rowIdx)) {
                throw new IllegalArgumentException("No card at position " + choice);
            }
        }

        for (char choice : choices) {
            int[] coords = charToMarketPosition(choice);

            int columnIdx = coords[0];
            int rowIdx = coords[1];

            Logger.debug("Taking card at " + columnIdx + ", " + rowIdx);
            Card card = market.takeCard(columnIdx, rowIdx);
            hand.addVeggieCard(card);
        }

        Logger.debug("Refilling market");
        state.refillMarketCards();
    }
}
