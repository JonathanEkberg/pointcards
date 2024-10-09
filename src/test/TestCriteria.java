package test;

import static org.junit.Assert.assertEquals;
import static test.utils.Hand.createHand;

import org.junit.Test;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.CriteriaFewest;
import pointcards.game.pointsalad.criteria.CriteriaMost;
import pointcards.game.pointsalad.criteria.ICriteria;

public class TestCriteria {
    private class CriteriaTest {
        private static final int OPPONENT_COUNT = 3;
        private final String testName;
        private final ICriteria criteria;
        private final int expectedPoints;
        private final int ownerCardAmount;
        private final int opponentsCardAmount;

        public CriteriaTest(String testName, ICriteria criteria, int expectedPoints, int ownerCardAmount,
                int opponentsCardAmount) {
            this.testName = testName;
            this.criteria = criteria;
            this.expectedPoints = expectedPoints;
            this.ownerCardAmount = ownerCardAmount;
            this.opponentsCardAmount = opponentsCardAmount;
        }

        public void run() {
            Card[] owner = createHand(Veggie.CARROT, ownerCardAmount);
            Card[][] opponents = new Card[OPPONENT_COUNT][];
            for (int i = 0; i < OPPONENT_COUNT; i++) {
                opponents[i] = createHand(Veggie.CARROT, opponentsCardAmount);
            }

            var points = criteria.calculatePoints(owner, opponents);
            assertEquals(this.testName, points, expectedPoints);
        }
    }

    @Test
    public void testCriterias() {
        CriteriaTest[] tests = new CriteriaTest[] {
                // Most cards
                new CriteriaTest("[MOST]: Does not have most", new CriteriaMost(Veggie.CARROT, 3), 0, 1, 3),
                new CriteriaTest("[MOST]: Has most", new CriteriaMost(Veggie.CARROT, 3), 3, 3, 2),
                // Fewest cards
                new CriteriaTest("[FEWEST]: Does not have fewest", new CriteriaFewest(Veggie.CARROT, 3), 0, 3, 1),
                new CriteriaTest("[FEWEST]: Has fewest", new CriteriaFewest(Veggie.CARROT, 3), 3, 1, 3),
        };

        for (CriteriaTest test : tests) {
            test.run();
        }
    }

}
