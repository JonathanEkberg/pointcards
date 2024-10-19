package test.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Deck;
import pointcards.game.pointsalad.Decks;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.HumanPlayer;
import pointcards.game.pointsalad.Market;
import pointcards.game.pointsalad.manifest.json.JSONManifestParser;
import pointcards.game.pointsalad.phases.InitPhase;
import test.utils.TestUtils;

public class Test06RandomStart {
    @Test
    public void test() {
        try {
            JSONManifestParser parser = TestUtils.getManifestParser("PSManifestV1.json");
            List<Card> cards = parser.getCards();

            // The amount of times this player was the starting player
            Map<Integer, Integer> playerStarts = new HashMap<>();

            List<HumanPlayer> players = TestUtils.createHumanPlayers(6);

            for (int i = 0; i < 1000; i++) {
                Deck gameDeck = new Deck(cards);

                GameState state = new GameState(players, new ArrayList<>(), gameDeck);
                InitPhase phase = new InitPhase(state);
                phase.run();
                state = phase.getState();
                int code = state.turner.getTurn().hashCode();

                if (!playerStarts.containsKey(code)) {
                    playerStarts.put(code, 0);
                }

                int current = playerStarts.get(code) + 1;
                playerStarts.put(code, current);
            }

            // Check that the starts for the players are roughly similar
            for (HumanPlayer player : players) {
                int starts = playerStarts.get(player.hashCode());
                // Check that no player started less than 120 times
                // (1/6 of 1000 = 166.666 ~ 120)
                assertTrue(
                        String.format("Player %d started too few times. Expected at least 120 but got %d",
                                player.hashCode(),
                                starts),
                        starts > 120);
            }

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Failed to read manifest file", false);
        }
    }
}
