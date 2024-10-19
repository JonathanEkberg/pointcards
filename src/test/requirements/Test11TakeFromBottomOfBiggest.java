package test.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Deck;
import pointcards.game.pointsalad.Decks;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.Hand;
import pointcards.game.pointsalad.HumanPlayer;
import pointcards.game.pointsalad.Market;
import pointcards.game.pointsalad.manifest.json.JSONManifestParser;
import pointcards.game.pointsalad.phases.InitPhase;
import pointcards.game.pointsalad.phases.PlayerTurnPhase;
import pointcards.io.input.IInput;
import pointcards.io.output.IOutput;
import pointcards.utils.Shuffler;
import test.utils.DummyOutput;
import test.utils.TestUtils;

public class Test11TakeFromBottomOfBiggest {
    private class TakesVeggieCardInput implements IInput {
        @Override
        public int queryInt(String query) {
            throw new RuntimeException("Not implemented");
        }

        @Override
        public int queryInt(String query, int min, int max) {
            throw new RuntimeException("Not implemented");
        }

        @Override
        public String queryString(String message) {
            // Take first cards from top of first and second column
            return "ab";
        }
    }

    private class TakesPointCardInput implements IInput {
        private int stringQueries = 1;

        @Override
        public int queryInt(String query) {
            throw new RuntimeException("Not implemented");
        }

        @Override
        public int queryInt(String query, int min, int max) {
            throw new RuntimeException("Not implemented");
        }

        @Override
        public String queryString(String message) {
            // Answer no to converting point cards to veggies
            if (stringQueries++ % 2 == 0) {
                return "n";
            }

            // Take first cards from top of first and second column
            return "1";
        }
    }

    private class GetHandOutput implements IOutput {
        private boolean hasGottenHandMessage = false;

        @Override
        public void send(String message) {
            this.hasGottenHandMessage = message.toLowerCase().contains("hand");
        }

        public boolean hasGottenHandMessage() {
            return this.hasGottenHandMessage;
        }
    }

    @Test
    public void test() {
        try {
            // Causes player 2 to be the starting player
            Shuffler.setSeed(123);

            JSONManifestParser parser = TestUtils.getManifestParser("PSManifestV1.json");
            List<Card> cards = parser.getCards();
            Deck gameDeck = new Deck(cards);
            gameDeck.shuffle();

            HumanPlayer player1 = new HumanPlayer("Player 1", new TakesVeggieCardInput(), new DummyOutput(),
                    new Hand());
            HumanPlayer player2 = new HumanPlayer("Player 2", new TakesPointCardInput(), new GetHandOutput(),
                    new Hand());

            List<HumanPlayer> players = List
                    .of(player1, player2);
            GameState state = new GameState(
                    players,
                    new ArrayList<>(),
                    gameDeck);
            new InitPhase(state).run();

            Decks decks = state.getDecks();
            assertEquals("Should have three decks", 3, decks.size());

            Deck firstDeck = decks.getDeck(0);
            Deck secondDeck = decks.getDeck(1);
            Deck thirdDeck = decks.getDeck(2);

            // Remove all cards from first deck
            while (firstDeck.getCards().size() > 0) {
                firstDeck.takeCard();
            }
            assertEquals("First deck should should be empty", 0, firstDeck.size());

            // Make third deck the largest deck
            while (secondDeck.getCards().size() + 10 >= thirdDeck.getCards().size()) {
                secondDeck.takeCard();
            }

            Market market = state.getMarket();
            // Remove all cards from first column in market
            market.takeCard(0, 0);
            market.takeCard(0, 1);
            assertTrue("Market column 1 should be empty", !market.hasCard(0, 0) && !market.hasCard(0, 1));

            int thirdDeckSizeBefore = thirdDeck.size();
            Card topOfThirdDeck = thirdDeck.getCards().get(0);
            state.refillMarketCards();

            assertEquals("Third deck should have shrunk by 2", thirdDeck.size(), thirdDeckSizeBefore - 2);
            assertTrue("Market column 2 is now full", market.hasCard(0, 0) && market.hasCard(0, 1));
            assertEquals("Top of third deck should stay the same", topOfThirdDeck, thirdDeck.getCards().get(0));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Failed to read manifest file", false);
        }
    }
}
