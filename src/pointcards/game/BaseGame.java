package pointcards.game;

import java.util.Optional;

public class BaseGame implements IGame {
    private IPhase startPhase;

    public BaseGame(IPhase startPhase) {
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
