package pointcards.game.pointsalad.phases;

import java.util.List;
import java.util.Optional;

import pointcards.game.Participant;
import pointcards.game.pointsalad.GameState;
import pointcards.game.IPhase;
import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Deck;
import pointcards.game.pointsalad.Decks;
import pointcards.game.pointsalad.Hand;
import pointcards.game.pointsalad.HumanPlayer;
import pointcards.utils.Logger;

public class InitPhase implements IPhase {
    private final GameState state;

    public InitPhase(GameState state) {
        // Okay assertion for now but should probably be changed later to merge all
        // decks into one
        assert state.getDecks().size() == 1 : "Deck size is not 1";
        this.state = state;
    }

    @Override
    public Optional<IPhase> run() {
        state.sendMessageToAllPlayers("Welcome to Point Salad!");
        Logger.debug("Running InitPhase");
        Decks decks = state.getDecks();
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
            state.getMarket().addCard(j, marketCards[j]);
        }
        Logger.debug("Market cards: " + marketCards[0] + ", " + marketCards[1] + ", " + marketCards[2]);

        Participant turn = state.turner.getTurn();

        if (turn instanceof HumanPlayer) {
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
