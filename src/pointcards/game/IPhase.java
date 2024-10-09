package pointcards.game;

import java.util.Optional;

public interface IPhase {
    public Optional<IPhase> run();
}
