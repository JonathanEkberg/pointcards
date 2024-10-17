package pointcards.game.pointsalad;

import java.util.List;

import pointcards.utils.Logger;

public class GameStatePrinter {
    private final GameState state;

    public GameStatePrinter(GameState state) {
        this.state = state;
    }

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

        // for (int i = 0; i < 6; i++) {
        // int columnIndex = i % 3;
        // int rowIndex = i % 2;
        // boolean shouldLineBreak = rowIndex > 1 && columnIndex == 2;

        // if (!market.hasCard(columnIndex, rowIndex)) {
        // sb.append('\t');
        // sb.append(shouldLineBreak ? '\n' : '\t');
        // continue;
        // }

        // String veggie = market.getCard(columnIndex, rowIndex).getVeggie().toString();

        // // New line and start with two tags if is first column and not first row
        // if (rowIndex > 0 && columnIndex == 0) {
        // sb.append("\n\t\t");
        // }

        // char columnChar = (char) ('A' + i);
        // sb.append(String.format("[%c]: %s%s", columnChar, veggie, shouldLineBreak ?
        // '\n' : '\t'));
        // }

        return sb.toString();
    }

    public String getPlayerHand(IPlayer player) {
        StringBuilder sb = new StringBuilder();
        sb.append(getPlayerCriteriaCards(player));
        sb.append("\n");
        sb.append(getPlayerVeggieCards(player));
        return sb.toString();
        // sb.append("Criteria:\t");
        // Hand hand = player.getHand();

        // for (Card card : hand.getCriteriaCards()) {
        // sb.append(card.getCriteria().toString());
        // sb.append(", ");
        // }

        // return sb.toString();
    }

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

    public String getPlayerVeggieCards(IPlayer player) {
        StringBuilder sb = new StringBuilder();
        sb.append("Veggies:\t");
        Hand hand = player.getHand();

        List<Card> cards = hand.getVeggieCards();

        // for (Card card : cards) {
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
