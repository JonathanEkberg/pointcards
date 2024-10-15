package pointcards.game.pointsalad.phases;

import java.util.Optional;

import pointcards.game.IPhase;
import pointcards.game.pointsalad.GameState;

public class FinishPhase implements IPhase {
    private final GameState state;

    public FinishPhase(GameState state) {
        this.state = state;
    }

    @Override
    public Optional<IPhase> run() {
        System.out.println("Game finished. Everybody wins!");
        return Optional.empty();
    }
}
