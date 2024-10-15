package pointcards.game.pointsalad.phases;

import java.util.Optional;

import pointcards.game.IPhase;
import pointcards.game.pointsalad.GameState;

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
