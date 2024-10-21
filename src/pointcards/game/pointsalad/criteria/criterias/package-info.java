/**
 * The `criterias` package contains classes that define specific criteria for
 * scoring points in the Point Salad game.
 * Each criterion specifies a condition that must be met to earn points. These
 * criteria can be combined to form complex
 * scoring rules.
 * 
 * This package includes the following criteria:
 * 
 * - {@link pointcards.game.pointsalad.criteria.criterias.CriteriaAtLeast}: A
 * criterion that awards points if the player
 * has at least a specified number of a particular veggie type.
 * - {@link pointcards.game.pointsalad.criteria.criterias.CriteriaEach}: A
 * criterion that awards points for each specified
 * veggie type the player has.
 * - {@link pointcards.game.pointsalad.criteria.criterias.CriteriaEven}: A
 * criterion that awards points if the player has
 * an even number of a particular veggie type.
 * - {@link pointcards.game.pointsalad.criteria.criterias.CriteriaFewest}: A
 * criterion that awards points if the player
 * has the fewest of a particular veggie type compared to opponents.
 * - {@link pointcards.game.pointsalad.criteria.criterias.CriteriaFewestTotal}:
 * A criterion that awards points if the player
 * has the fewest total cards compared to opponents.
 * - {@link pointcards.game.pointsalad.criteria.criterias.CriteriaMost}: A
 * criterion that awards points if the player has
 * the most of a particular veggie type compared to opponents.
 * - {@link pointcards.game.pointsalad.criteria.criterias.CriteriaMostTotal}: A
 * criterion that awards points if the player
 * has the most total cards compared to opponents.
 * - {@link pointcards.game.pointsalad.criteria.criterias.CriteriaOdd}: A
 * criterion that awards points if the player has
 * an odd number of a particular veggie type.
 * - {@link pointcards.game.pointsalad.criteria.criterias.CriteriaPer}: A
 * criterion that awards points for each instance
 * of a particular veggie type the player has.
 * -
 * {@link pointcards.game.pointsalad.criteria.criterias.CriteriaPerMissingType}:
 * A criterion that awards points for each
 * missing veggie type the player does not have.
 * - {@link pointcards.game.pointsalad.criteria.criterias.CriteriaSet}: A
 * criterion that awards points based on the number
 * of complete sets of different veggie types the player has.
 */
package pointcards.game.pointsalad.criteria.criterias;