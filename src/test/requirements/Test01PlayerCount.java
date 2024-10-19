package test.requirements;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pointcards.game.BasePlayer;
import pointcards.game.pointsalad.GameFactory;
import test.utils.DummyInput;
import test.utils.DummyOutput;

public class Test01PlayerCount {
    @Test
    public void test() {
        try {
            for (int i = 0; i <= 7; i++) {
                GameFactory factory = new GameFactory("PSManifestV1.json");
                List<BasePlayer> players = new ArrayList<>(i);

                for (int j = 0; j < i; j++) {
                    players.add(new BasePlayer("Player " + j, new DummyInput(), new DummyOutput()));
                }

                try {
                    factory.createGame(players);
                } catch (IllegalArgumentException e) {
                    if (i >= 2 && i <= 6) {
                        assertNull(String.format("Failed to create game with %d players", i), e);
                    } else {
                        assertNotNull(String.format("Should not be able to create game with %d players", i), e);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Failed to read manifest file", false);
        }
    }
}
