package pointcards.game.pointsalad.criteria;

import pointcards.game.pointsalad.concepts.Card;

/**
 * The {@code Criterias} class represents a collection of criteria, allowing
 * multiple criteria to be combined. It implements the {@code ICriteria}
 * interface
 * and calculates the total points earned by summing the points from each
 * individual
 * criterion.
 */
public class Criterias implements ICriteria {
    private final ICriteria[] criterias;

    /**
     * Constructs a {@code Criterias} object with an array of criteria.
     *
     * @param criterias an array of criteria to be combined
     */
    public Criterias(ICriteria[] criterias) {
        this.criterias = criterias;
    }

    /**
     * Constructs a {@code Criterias} object with a single criterion.
     *
     * @param criteria a single criterion to be included
     */
    public Criterias(ICriteria criteria) {
        this(new ICriteria[] { criteria });
    }

    /**
     * Calculates the total points earned by summing the points from each individual
     * criterion.
     *
     * @param owner     the cards owned by the player
     * @param opponents the cards owned by the opponents
     * @return the total points earned based on the combined criteria
     */
    @Override
    public int calculatePoints(Card[] owner, Card[][] opponents) {
        int points = 0;

        for (ICriteria criteria : this.criterias) {
            points += criteria.calculatePoints(owner, opponents);
        }

        return points;
    }

    /**
     * Returns a string representation of the combined criteria.
     *
     * @return a string representation of the combined criteria
     */
    @Override
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
