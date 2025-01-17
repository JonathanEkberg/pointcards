package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pointcards.game.pointsalad.concepts.Card;
import pointcards.game.pointsalad.concepts.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;
import pointcards.game.pointsalad.criteria.criterias.CriteriaEven;
import pointcards.game.pointsalad.criteria.criterias.CriteriaFewest;
import pointcards.game.pointsalad.criteria.criterias.CriteriaMost;
import pointcards.game.pointsalad.criteria.criterias.CriteriaOdd;
import test.utils.TestUtils;

public class TestCriteria {
    private class SingleCriteriaTest {
        private static final int OPPONENT_COUNT = 3;
        private final String testName;
        private final ICriteria criteria;
        private final int expectedPoints;
        private final int ownerCardAmount;
        private final int opponentsCardAmount;

        public SingleCriteriaTest(String testName, ICriteria criteria, int expectedPoints, int ownerCardAmount,
                int opponentsCardAmount) {
            this.testName = testName;
            this.criteria = criteria;
            this.expectedPoints = expectedPoints;
            this.ownerCardAmount = ownerCardAmount;
            this.opponentsCardAmount = opponentsCardAmount;
        }

        public void run() {
            Card[] owner = TestUtils.createHand(Veggie.CARROT, ownerCardAmount);
            Card[][] opponents = new Card[OPPONENT_COUNT][];
            for (int i = 0; i < OPPONENT_COUNT; i++) {
                opponents[i] = TestUtils.createHand(Veggie.CARROT, opponentsCardAmount);
            }

            var points = criteria.calculatePoints(owner, opponents);
            assertEquals(this.testName, points, expectedPoints);
        }
    }

    @Test
    public void testCriterias() {
        SingleCriteriaTest[] tests = new SingleCriteriaTest[] {
                // Most cards
                new SingleCriteriaTest("[MOST]: Does not have most", new CriteriaMost(Veggie.CARROT, 3), 0, 1, 3),
                new SingleCriteriaTest("[MOST]: Has most", new CriteriaMost(Veggie.CARROT, 3), 3, 3, 2),
                // Fewest cards
                new SingleCriteriaTest("[FEWEST]: Does not have fewest", new CriteriaFewest(Veggie.CARROT, 3), 0, 3, 1),
                new SingleCriteriaTest("[FEWEST]: Has fewest", new CriteriaFewest(Veggie.CARROT, 3), 3, 1, 3),
                // Even cards
                new SingleCriteriaTest("[EVEN]: Does not have even", new CriteriaEven(Veggie.CARROT, 3), 0, 3, 0),
                new SingleCriteriaTest("[EVEN]: Has even", new CriteriaEven(Veggie.CARROT, 3), 3, 2, 0),
                // Odd cards
                new SingleCriteriaTest("[ODD]: Does not have odd", new CriteriaOdd(Veggie.CARROT, 3), 0, 2, 0),
                new SingleCriteriaTest("[ODD]: Has odd", new CriteriaOdd(Veggie.CARROT, 3), 3, 3, 0),
                // Omitted tests for remaining criteria as it is not required for the exam.
        };
        for (SingleCriteriaTest test : tests) {
            test.run();
        }
    }

}
