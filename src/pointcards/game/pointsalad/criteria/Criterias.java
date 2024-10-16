package pointcards.game.pointsalad.criteria;

import pointcards.game.pointsalad.Card;

public class Criterias implements ICriteria {
    private final ICriteria[] criterias;

    public Criterias(ICriteria[] criterias) {
        this.criterias = criterias;
    }

    public Criterias(ICriteria criteria) {
        this(new ICriteria[] { criteria });
    }

    @Override
    public int calculatePoints(Card[] owner, Card[][] opponents) {
        int points = 0;

        for (ICriteria criteria : this.criterias) {
            points += criteria.calculatePoints(owner, opponents);
        }

        return points;
    }

    public String toString() {
        if (this.criterias.length == 1) {
            return this.criterias[0].toString();
        }

        StringBuilder sb = new StringBuilder();

        for (ICriteria criteria : this.criterias) {
            sb.append(criteria.toString());
            sb.append(", ");
        }

        return sb.toString();
    }
}
