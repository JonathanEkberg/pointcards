package pointcards.game.pointsalad;

import java.util.List;

/**
 * The GameStatePrinter class provides methods to generate string
 * representations
 * of the point cards and veggie cards available in the game.
 */
public class GameStatePrinter {
    private final GameState state;

    /**
     * Constructs a GameStatePrinter with the specified game state.
     *
     * @param state the current game state
     */
    public GameStatePrinter(GameState state) {
        this.state = state;
    }

    /**
     * Generates a string representation of the available point cards.
     *
     * @return a string representing the available point cards
     */
    public String getPointCardChoics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Point Cards:\t");

        int pile = -1;
        for (var deck : state.getDecks().getDecks()) {
            pile += 1;
            List<Card> cards = deck.getCards();

            if (cards.size() == 0) {
                sb.append('\t');
                continue;
            }

            sb.append(String.format("[%d] ", pile));
            Card card = deck.getCards().get(0);
            sb.append(card.getCriteria().toString());
            sb.append(String.format(" (%s)", card.getVeggie().toString()));
            sb.append('\t');
        }

        return sb.toString();
    }

    /**
     * Generates a string representation of the available veggie cards.
     *
     * @return a string representing the available veggie cards
     */
    public String getVeggieCardChoics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Veggie Cards:\t");
        Market market = state.getMarket();

        for (int row = 0; row < 2; row++) {
            for (int column = 0; column < 3; column++) {
                boolean shouldLineBreak = row > 1 && column == 2;

                if (!market.hasCard(column, row)) {
                    sb.append('\t');

                    if (shouldLineBreak) {
                        sb.append('\n');
                    } else {
                        sb.append('\t');
                    }

                    continue;
                }

                // New line and start with two tags if is first column and not first row
                if (row > 0 && column == 0) {
                    sb.append("\n\t\t");
                }

                Card card = market.getCard(column, row);
                sb.append(String.format("[%c] %s%c", (char) ('A' + (column + row * 3)), card.getVeggie().toString(),
                        shouldLineBreak ? '\n' : '\t'));
            }
        }

        return sb.toString();
    }

    /**
     * Generates a string representation of the player's hand, including both
     * criteria and veggie cards.
     *
     * @param player the player whose hand is to be represented
     * @return a string representing the player's hand
     */
    public String getPlayerHand(IPlayer player) {
        StringBuilder sb = new StringBuilder();
        sb.append(getPlayerCriteriaCards(player));
        sb.append("\n");
        sb.append(getPlayerVeggieCards(player));
        return sb.toString();
    }

    /**
     * Generates a string representation of the player's criteria cards.
     *
     * @param player the player whose criteria cards are to be represented
     * @return a string representing the player's criteria cards
     */
    public String getPlayerCriteriaCards(IPlayer player) {
        StringBuilder sb = new StringBuilder();
        sb.append("Criteria:\t");
        Hand hand = player.getHand();

        List<Card> cards = hand.getCriteriaCards();
        for (Card card : cards) {
            sb.append(card.getCriteria().toString());

            if (cards.size() > 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    /**
     * Retrieves a string representation of the vegetable cards in a player's hand.
     *
     * @param player the player whose vegetable cards are to be retrieved
     * @return a string listing the vegetable cards in the player's hand, separated
     *         by commas
     */
    public String getPlayerVeggieCards(IPlayer player) {
        StringBuilder sb = new StringBuilder();
        sb.append("Veggies:\t");
        Hand hand = player.getHand();

        List<Card> cards = hand.getVeggieCards();

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            sb.append(card.getVeggie().toString());

            if (cards.size() > 1 && i < cards.size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
