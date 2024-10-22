package test.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.concepts.Card;
import pointcards.game.pointsalad.concepts.Deck;
import pointcards.game.pointsalad.concepts.Decks;
import pointcards.game.pointsalad.concepts.Market;
import pointcards.game.pointsalad.manifest.json.JSONManifestParser;
import pointcards.game.pointsalad.phases.InitPhase;
import test.utils.TestUtils;

public class Test05FlipOver6Cards {
    @Test
    public void test() {
        try {
            JSONManifestParser parser = TestUtils.getManifestParser("PSManifestV1.json");
            List<Card> cards = parser.getCards();
            Deck gameDeck = new Deck(cards);
            GameState state = new GameState(TestUtils.createHumanPlayers(6), new ArrayList<>(), gameDeck);
            assertEquals("Starts with only 1 deck", state.getDecks().size(), 1);
            assertEquals("First deck size 108", state.getDecks().getDeck(0).size(), 108);
            InitPhase phase = new InitPhase(state);
            phase.run();
            state = phase.getState();
            Decks decks = state.getDecks();

            int totalCards = 0;

            for (Deck deck : decks.getDecks()) {
                totalCards += deck.size();
            }

            assertEquals("Decks don't have 102 cards (108 - 6 in market)", 102, totalCards);

            Market market = state.getMarket();
            int columns = market.getColumns();
            int rows = market.getRows();

            int uncheckedCards = 6;

            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    assertTrue("Market card is not flipped", market.hasCard(i, j));
                    uncheckedCards--;
                }
            }
            assertEquals("Didn't find exactly 6 market cards", 0, uncheckedCards);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Failed to read manifest file", false);
        }
    }
}
