package test.requirements;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import pointcards.game.BaseBot;
import pointcards.game.pointsalad.GameFactory;
import pointcards.game.pointsalad.PointSaladGame;

public class Test12To14PlayGame {
    @Test
    public void test() {
        try {
            GameFactory factory = new GameFactory("PSManifestV1.json");

            BaseBot bot1 = new BaseBot("Bot 1");
            BaseBot bot2 = new BaseBot("Bot 2");

            PointSaladGame game = factory.createGame(new ArrayList<>(), List.of(bot1, bot2));
            game.run();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            assertTrue("Failed to read manifest file", false);
        }
    }
}
