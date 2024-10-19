package test.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import pointcards.game.IPhase;
import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Deck;
import pointcards.game.pointsalad.Decks;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.GameStatePrinter;
import pointcards.game.pointsalad.Hand;
import pointcards.game.pointsalad.HumanPlayer;
import pointcards.game.pointsalad.Market;
import pointcards.game.pointsalad.manifest.json.JSONManifestParser;
import pointcards.game.pointsalad.phases.InitPhase;
import pointcards.game.pointsalad.phases.PlayerTurnPhase;
import pointcards.io.input.IInput;
import pointcards.io.output.IOutput;
import pointcards.io.output.LocalConsoleOutput;
import pointcards.utils.Randomizer;
import test.utils.DummyOutput;
import test.utils.TestUtils;

public class Test07And09And10PlayerTake {
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
            Randomizer.setSeed(123);

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

            InitPhase initPhase = new InitPhase(state);
            PlayerTurnPhase playerPhase = (PlayerTurnPhase) initPhase.run().get();
            Card initialSecondDeckTopCard = state.getDecks().getDeck(1).getCards().get(0);

            playerPhase = (PlayerTurnPhase) playerPhase.run().get();
            state = playerPhase.getState();

            assertFalse("Player 1 should have taken a point card",
                    state.getDecks().getDeck(1).getCards().get(0).equals(initialSecondDeckTopCard));

            // All the initial market cards. Only the first and second card (top row from
            // column 1 and 2) should change after the player has played since they take
            // 'ab'
            Card[] initialCards = new Card[] {
                    state.getMarket().getCard(0, 0),
                    state.getMarket().getCard(1, 0),
                    state.getMarket().getCard(2, 0),
                    state.getMarket().getCard(0, 1),
                    state.getMarket().getCard(1, 1),
                    state.getMarket().getCard(2, 1)
            };

            playerPhase = (PlayerTurnPhase) playerPhase.run().get();
            state = playerPhase.getState();

            for (int i = 0; i < 2; i++) {
                // Market refilled
                assertNotSame("Market card should not be the same after player 1 turn",
                        initialCards[i], state.getMarket().getCard(i % 3, i / 3));
            }

            for (int i = 2; i < 6; i++) {
                assertEquals("Market card should be the same after player 1 turn",
                        initialCards[i], state.getMarket().getCard(i % 3, i / 3));
            }
            assertTrue("Player should have gotten hand output",
                    ((GetHandOutput) player2.getOutput()).hasGottenHandMessage());
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Failed to read manifest file", false);
        }
    }
}
