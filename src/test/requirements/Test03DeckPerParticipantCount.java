package test.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.GameFactory;
import test.utils.TestUtils;

public class Test03DeckPerParticipantCount {
    class TestGameFactory extends GameFactory {
        public TestGameFactory(String manifestPath) throws Exception {
            super(manifestPath);
        }

        public List<Card> participantCountCards(int participantCount) {
            return this.participantCountToCards(participantCount);
        }
    }

    @Test
    public void test() {
        try {
            TestGameFactory factory = new TestGameFactory("./PSManifestV1.json");
            factory.createGame(TestUtils.createBasePlayers(6));

            for (int i = 2; i <= 6; i++) {
                List<Card> deck = factory.participantCountCards(i);

                switch (i) {
                    case 2:
                        assertEquals("Pointsalad JSON manifest doesn't have 36 cards for 2 participants", 36,
                                deck.size());
                        break;
                    case 3:
                        assertEquals("Pointsalad JSON manifest doesn't have 54 cards for 3 participants", 54,
                                deck.size());
                        break;
                    case 4:
                        assertEquals("Pointsalad JSON manifest doesn't have 72 cards for 4 participants", 72,
                                deck.size());
                        break;
                    case 5:
                        assertEquals("Pointsalad JSON manifest doesn't have 90 cards for 5 participants", 90,
                                deck.size());
                        break;
                    case 6:
                        assertEquals("Pointsalad JSON manifest doesn't have 108 cards for 6 participants", 108,
                                deck.size());
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Failed to read manifest file", false);
        }
    }
}
