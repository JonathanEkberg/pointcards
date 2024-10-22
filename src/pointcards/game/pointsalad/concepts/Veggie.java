package pointcards.game.pointsalad.concepts;

/**
 * The {@code Veggie} enum represents the different types of vegetables
 * available in the Point Salad game. Each vegetable type can be used in
 * various game mechanics, such as scoring criteria and card drafting.
 * 
 * <p>
 * The available vegetable types are:
 * </p>
 * <ul>
 * <li>{@link #CARROT}</li>
 * <li>{@link #LETTUCE}</li>
 * <li>{@link #TOMATO}</li>
 * <li>{@link #ONION}</li>
 * <li>{@link #PEPPER}</li>
 * <li>{@link #CABBAGE}</li>
 * </ul>
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * {@code
 * Veggie veggie = Veggie.CARROT;
 * System.out.println("Selected vegetable: " + veggie);
 * }
 * </pre>
 * 
 * @see pointcards.game.pointsalad.concepts.Card
 * @see pointcards.game.pointsalad.criteria.ICriteria
 */
public enum Veggie {
    CARROT,
    LETTUCE,
    TOMATO,
    ONION,
    PEPPER,
    CABBAGE
}
