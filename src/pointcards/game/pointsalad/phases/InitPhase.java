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
import pointcards.game.pointsalad.Market;
import pointcards.utils.Logger;

public class InitPhase implements IPhase {
    private final GameState state;

    public InitPhase(GameState state) {
        // Okay assertion for now but should probably be changed later to merge all
        // decks into one
        assert state.getDecks().size() == 1 : "Deck size is not 1";
        this.state = state;
    }

    public GameState getState() {
        return this.state;
    }

    @Override
    public Optional<IPhase> run() {
        state.sendMessageToAllPlayers("Welcome to Point Salad!");
        Logger.debug("Running InitPhase");
        Decks decks = state.getDecks();
        this.state.setDecks(InitPhase.splitDeck(decks));

        // Grab market cards
        Card[] marketCards = new Card[6];
        for (int i = 0; i < 6; i++) {
            int j = i % 3;
            Deck deck = state.getDecks().getDeck(j);
            assert deck.size() > 1 : "Deck size is not greater than 2";

            Card card = deck.takeCard().get();

            marketCards[i] = card;
        }

        // Place market cards
        Market market = state.getMarket();
        for (int i = 0; i < 6; i++) {
            int j = i % 3;
            market.addCard(j, marketCards[i]);
        }

        Participant turn = state.turner.getTurn();

        if (turn instanceof HumanPlayer) {
            return Optional.of(new PlayerTurnPhase(state));
        }

        return Optional.of(new BotTurnPhase(state));
    }

    /**
     * Convert a single deck into three decks ready for play.
     */
    public static Decks splitDeck(Decks singleDeck) {
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
