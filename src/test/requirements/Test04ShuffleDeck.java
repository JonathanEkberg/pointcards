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
import pointcards.game.pointsalad.manifest.json.JSONManifestParser;
import pointcards.game.pointsalad.phases.InitPhase;
import test.utils.TestUtils;

public class Test04ShuffleDeck {
    // class TestInitPhase extends InitPhase {
    // public TestInitPhase(GameState state) {
    // super(state);
    // }

    // public GameState getState() {
    // return this.state;
    // }
    // }

    @Test
    public void test() {
        try {
            JSONManifestParser parser = TestUtils.getManifestParser("PSManifestV1.json");
            List<Card> cards = parser.getCards();
            Deck gameDeck = new Deck(cards);
            GameState state = new GameState(TestUtils.createHumanPlayers(6), new ArrayList<>(), gameDeck);
            assertEquals("Starts with only 1 deck", state.getDecks().size(), 1);
            assertEquals("First deck size 108", state.getDecks().getDeck(0).size(), 108);
            // InitPhase phase = new InitPhase(state);
            Decks splitDecks = InitPhase.splitDeck(new Decks(List.of(gameDeck)));

            int totalCards = 0;
            for (Deck deck : splitDecks.getDecks()) {
                totalCards += deck.size();
            }
            assertEquals("All cards are in the deck", 108, totalCards);
            // phase.run();
            // state = phase.getState();
            // Decks decks = state.getDecks();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Failed to read manifest file", false);
        }
    }
}
