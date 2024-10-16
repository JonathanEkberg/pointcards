package pointcards.game.pointsalad.phases;

import java.util.Optional;

import pointcards.game.pointsalad.GameState;
import pointcards.game.IPhase;

public class BotTurnPhase implements IPhase {
    private final GameState state;

    public BotTurnPhase(GameState state) {
        this.state = state;
    }

    @Override
    public Optional<IPhase> run() {
        return Optional.of(new FinishPhase(state));
    }
}
