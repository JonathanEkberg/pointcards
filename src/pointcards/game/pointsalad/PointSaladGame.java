package pointcards.game.pointsalad;

import pointcards.game.BaseGame;
import pointcards.game.pointsalad.phases.InitPhase;

/**
 * The {@code PointSaladGame} class represents the Point Salad game.
 * It extends the {@link pointcards.game.BaseGame} class and initializes the
 * game
 * with the {@link pointcards.game.pointsalad.phases.InitPhase}.
 * 
 * <p>
 * The Point Salad game is a card-drafting game where players collect and score
 * points based on the cards they draft. This class sets up the initial game
 * state and starts the game with the initial phase.
 * </p>
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * {@code
 * GameState gameState = new GameState(players, decks, market);
 * PointSaladGame game = new PointSaladGame(gameState);
 * game.run();
 * }
 * </pre>
 * 
 * @see pointcards.game.BaseGame
 * @see pointcards.game.pointsalad.phases.InitPhase
 */
public class PointSaladGame extends BaseGame {
    /**
     * Constructs a new {@code PointSaladGame} with the specified game state.
     * 
     * @param state The initial game state.
     */
    public PointSaladGame(GameState state) {
        super(new InitPhase(state));
    }
}
