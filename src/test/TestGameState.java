package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.concepts.Card;
import pointcards.game.pointsalad.concepts.Deck;
import pointcards.game.pointsalad.concepts.Hand;
import pointcards.game.pointsalad.concepts.Market;
import pointcards.game.pointsalad.manifest.json.JSONManifestParser;
import pointcards.game.pointsalad.participants.HumanPlayer;
import pointcards.game.pointsalad.phases.InitPhase;
import pointcards.io.input.IInput;
import test.utils.DummyOutput;
import test.utils.TestUtils;

public class TestGameState {
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

    @Test
    public void testWhenDecksAreEmpty() {
        try {
            JSONManifestParser parser = TestUtils.getManifestParser("PSManifestV1.json");
            List<Card> cards = parser.getCards();
            Deck gameDeck = new Deck(cards);
            List<HumanPlayer> players = new ArrayList<>();
            players.add(new HumanPlayer("Player 1", new TakesVeggieCardInput(), new DummyOutput(), new Hand()));
            players.addAll(TestUtils.createHumanPlayers(5));
            GameState state = new GameState(players, new ArrayList<>(), gameDeck);
            InitPhase phase = new InitPhase(state);
            phase.run();

            for (Deck deck : state.getDecks().getDecks()) {
                while (deck.size() > 0) {
                    deck.takeCard();
                }
            }
            assertTrue("Deck should be zero", state.getDecks().allEmptyDecks());
            Market market = state.getMarket();
            int size = market.size();

            players.get(0).doTurn(state);
            assertTrue("Deck size should be 0", state.getDecks().allEmptyDecks());
            assertEquals("Market size should be two less", size - 2, market.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Could not find manifest file");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception thrown");
        }
    }
}
