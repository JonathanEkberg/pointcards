package test.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.manifest.json.JSONManifestParser;
import test.utils.TestUtils;

public class Test02Deck {
    @Test
    public void test() {
        try {
            JSONManifestParser manifestParser = TestUtils.getManifestParser("PSManifestV1.json");
            List<Card> cards = manifestParser.getCards();
            assertEquals("Pointsalad JSON manifest doesn't have 108 cards", cards.size(), 108);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Failed to read manifest file", false);
        }
    }
}
