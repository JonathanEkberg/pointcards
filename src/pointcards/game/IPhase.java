package pointcards.game;

import java.util.Optional;

/**
 * The `IPhase` interface defines the contract for a phase in the game.
 * A phase represents a distinct stage or step in the game's execution flow.
 * Implementations of this interface should provide the logic for running the
 * phase
 * and determining the next phase to transition to, if any.
 */
public interface IPhase {
    /**
     * Executes the logic for the current phase.
     * 
     * @return An {@link Optional} containing the next phase to transition to, or an
     *         empty {@link Optional}
     *         if there are no more phases to execute.
     */
    public Optional<IPhase> run();
}
