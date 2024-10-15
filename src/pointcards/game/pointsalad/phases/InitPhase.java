package pointcards.game.pointsalad.phases;

import java.util.List;
import java.util.Optional;

import pointcards.game.Entity;
import pointcards.game.IPhase;
import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Deck;
import pointcards.game.pointsalad.Decks;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.Market.ColumnFullException;
import pointcards.game.pointsalad.Player;
import pointcards.utils.Logger;

public class InitPhase implements IPhase {
    private final GameState state;

    public InitPhase(GameState state) {
        this.state = state;
    }

    @Override
    public Optional<IPhase> run() {
        Logger.debug("Running InitPhase");
        Decks decks = state.getDecks();
        // System.out.println("Deck:");
        // for (Card card : decks.getDeck(0).getCards()) {
        // System.out.println(card.getVeggie());
        // }
        assert decks.size() == 1 : "Deck size is not 1";
        this.state.setDecks(this.splitDeck(decks));

        Card[] marketCards = new Card[3];

        for (int i = 0; i < 6; i++) {
            int j = i % 3;
            Optional<Card> card = state.getDecks().getDeck(j).takeCard();

            if (!card.isPresent()) {
                throw new IllegalStateException("Deck is empty");
            }

            marketCards[j] = card.get();
        }

        for (int i = 0; i < 6; i++) {
            int j = i % 3;
            try {
                state.getMarket().addCard(j, marketCards[j]);
            } catch (ColumnFullException e) {
                e.printStackTrace();
            }
        }
        Logger.debug("Market cards: " + marketCards[0] + ", " + marketCards[1] + ", " + marketCards[2]);

        Entity turn = state.turner.getTurn();

        if (turn instanceof Player) {
            return Optional.of(new PlayerTurnPhase(state));
        }

        return Optional.of(new BotTurnPhase(state));
    }

    /**
     * Convert a single deck into three decks ready for play.
     */
    public Decks splitDeck(Decks singleDeck) {
        Deck deck = singleDeck.getDeck(0);
        List<Deck> decks = List.of(new Deck(), new Deck(), new Deck());

        int i = 2;
        for (Card card : deck.getCards()) {
            if (i == 2) {
                decks.get(0).addCard(card);
            } else if (i == 1) {
                decks.get(1).addCard(card);
            } else {
                decks.get(2).addCard(card);
            }

            i = (i + 1) % 3;
        }

        return new Decks(decks);
    }
}
