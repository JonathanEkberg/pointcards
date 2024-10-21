package pointcards.game;

import java.util.Optional;

/**
 * The `BaseGame` class implements the `IGame` interface and represents the core
 * game loop.
 * It manages the execution of game phases, starting from an initial phase and
 * continuing until there are no more phases to run.
 */
public class BaseGame implements IGame {
    private IPhase startPhase;

    /**
     * Constructs a `BaseGame` instance with the specified starting phase.
     * 
     * @param startPhase The initial phase to start the game with.
     */
    public BaseGame(IPhase startPhase) {
        this.startPhase = startPhase;
    }

    /**
     * Runs the game by executing phases in a loop until there are no more phases
     * to run.
     * The game starts with the initial phase and continues with subsequent phases
     * returned by each phase's `run` method.
     */
    public void run() {
        Optional<IPhase> phase = startPhase.run();

        // Play the game until we have no more phases at which point we have finished
        // playing the game.
        while (!phase.isEmpty()) {
            phase = phase.get().run();
        }
    }
}