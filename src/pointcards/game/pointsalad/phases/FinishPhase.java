package pointcards.game.pointsalad.phases;

import java.util.Optional;

import pointcards.game.pointsalad.GameState;
import pointcards.game.IPhase;

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
