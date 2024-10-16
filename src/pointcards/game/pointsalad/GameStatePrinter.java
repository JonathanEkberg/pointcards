package pointcards.game.pointsalad;

import java.util.List;

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

            sb.append(String.format("[%d]: ", pile));
            Card card = deck.getCards().get(0);
            sb.append(card.getCriteria().toString());
            sb.append(String.format(" (%s)", card.getVeggie().toString()));
            sb.append('\t');
        }

        return sb.toString();
    }
}
