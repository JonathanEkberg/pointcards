package test.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Deck;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.Hand;
import pointcards.game.pointsalad.HumanPlayer;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.criterias.CriteriaPer;
import pointcards.game.pointsalad.manifest.json.JSONManifestParser;
import pointcards.io.input.IInput;
import pointcards.io.output.LocalConsoleOutput;
import pointcards.utils.Logger;
import test.utils.DummyInput;
import test.utils.DummyOutput;
import test.utils.TestUtils;

public class Test08PlayerConvert {
    private class TakesPointCardAndConvertsInput implements IInput {
        int stringQueries = 1;

        @Override
        public int queryInt(String query) {
            Logger.debug("Converting criteria card at index 0");
            // Convert the point card at index 0
            return 0;
        }

        @Override
        public int queryInt(String query, int min, int max) {
            Logger.debug("Converting criteria card at index 0");
            // Convert the point card at index 0
            return 0;
        }

        @Override
        public String queryString(String message) {
            assertFalse("Hasn't been asked more than 10 times", stringQueries > 10);
            int step = stringQueries++ % 3;

            if (step == 1) {
                Logger.debug(message, "Taking first card from left most deck");
                // Take criteria from left most deck
                return "0";
            } else if (step == 2) {
                Logger.debug(message, "Answering yes to convert point card");
                // Answer yes, I want to convert one of my point cards
                return "y";
            } else {
                Logger.debug(message, "Converting criteria card at index 0");
                // Convert the point card at index 0
                return "0";
            }
        }
    }

    @Test
    public void testNew() {
        try {
            JSONManifestParser parser;
            parser = TestUtils.getManifestParser("PSManifestV1.json");
            List<Card> cards = parser.getCards();
            Deck gameDeck = new Deck(cards);
            gameDeck.shuffle();
            Hand hand = new Hand();

            Card initialCriteriaCard = new Card(Veggie.CABBAGE, new CriteriaPer(Veggie.CABBAGE, 1));
            hand.addCriteriasCard(initialCriteriaCard);

            HumanPlayer player = new HumanPlayer("Player 1", new TakesPointCardAndConvertsInput(),
                    new LocalConsoleOutput(),
                    hand);
            GameState state = new GameState(
                    List.of(player, new HumanPlayer("Player 2", new DummyInput(), new DummyOutput(), new Hand())),
                    new ArrayList<>(),
                    gameDeck);

            assertEquals("Player should have one criteria cards", 1, player.getHand().getCriteriaCards().size());
            player.doTurn(state);
            assertEquals("Player should still have one criteria cards", 1, player.getHand().getCriteriaCards().size());

            assertTrue("Player should have a single criteria card", player.getHand().getCriteriaCards().size() == 1);
            assertFalse("The single criteria card hasn't changed",
                    player.getHand().getCriteriaCards().get(0).equals(initialCriteriaCard));

            assertTrue("Player should have 1 veggie card", player.getHand().getVeggieCards().size() == 1);
            assertSame("The converted veggie card matches the initial criteria card", initialCriteriaCard,
                    player.getHand().getVeggieCards().get(0));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Failed to read manifest file", false);
        }
    }

    // @Test
    // public void test() {
    // try {
    // // Causes player 2 to be the starting player
    // Shuffler.setSeed(123);

    // JSONManifestParser parser = TestUtils.getManifestParser("PSManifestV1.json");
    // List<Card> cards = parser.getCards();
    // Deck gameDeck = new Deck(cards);
    // gameDeck.shuffle();

    // List<HumanPlayer> players = List
    // .of(new HumanPlayer("Player 1", new TakesPointCardAndConvertsInput(), new
    // DummyOutput(),
    // new Hand()),
    // new HumanPlayer("Player 2", new DummyInput(), new DummyOutput(), new
    // Hand()));
    // GameState state = new GameState(
    // players,
    // new ArrayList<>(),
    // gameDeck);

    // InitPhase initPhase = new InitPhase(state);
    // PlayerTurnPhase playerPhase = (PlayerTurnPhase) initPhase.run().get();
    // Card initialFirstDeckTopCard = state.getDecks().getDeck(0).getCards().get(0);

    // playerPhase = (PlayerTurnPhase) playerPhase.run().get();
    // state = playerPhase.getState();

    // assertFalse("Player 1 should have taken a point card",
    // state.getDecks().getDeck(0).getCards().get(0).equals(initialFirstDeckTopCard));

    // // All the initial market cards. Only the first and second card (top row from
    // // column 1 and 2) should change after the player has played since they take
    // // 'ab'
    // Card[] initialCards = new Card[] {
    // state.getMarket().getCard(0, 0),
    // state.getMarket().getCard(1, 0),
    // state.getMarket().getCard(2, 0),
    // state.getMarket().getCard(0, 1),
    // state.getMarket().getCard(1, 1),
    // state.getMarket().getCard(2, 1)
    // };

    // playerPhase = (PlayerTurnPhase) playerPhase.run().get();
    // state = playerPhase.getState();

    // for (int i = 0; i < 2; i++) {
    // assertNotSame("Market card should not be the same after player 1 turn",
    // initialCards[i], state.getMarket().getCard(i % 3, i / 3));
    // }

    // for (int i = 2; i < 6; i++) {
    // assertEquals("Market card should be the same after player 1 turn",
    // initialCards[i], state.getMarket().getCard(i % 3, i / 3));
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // assertTrue("Failed to read manifest file", false);
    // }
    // }
}
