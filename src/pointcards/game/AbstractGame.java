package pointcards.game;

import java.util.Optional;

public abstract class AbstractGame implements IGame {
    private IPhase startPhase;

    public AbstractGame(IPhase startPhase) {
        this.startPhase = startPhase;
    }

    public void run() {
        Optional<IPhase> phase = startPhase.run();

        // Play the game until we have no more phases at which point we have finished
        // playing the game.
        while (!phase.isEmpty()) {
            phase = phase.get().run();
        }
    }
}
