package pointcards.game.pointsalad.manifest;

import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;
import pointcards.game.pointsalad.criteria.criterias.CriteriaFewest;
import pointcards.game.pointsalad.criteria.criterias.CriteriaMost;

public class CriteriaFactory {
    public static ICriteria createCriteria(CriteriaType type, Veggie[] targets, int points) {
        switch (type) {
            case MOST:
                return new CriteriaMost(targets[0], points);
            case FEWEST:
                return new CriteriaFewest(targets[0], points);
            default:
                throw new IllegalArgumentException("Unknown criteria type: " + type);
        }
    }
}
