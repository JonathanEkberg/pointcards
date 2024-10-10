package pointcards.game.pointsalad;

import java.util.List;
import java.util.Optional;

import pointcards.game.Bot;
import pointcards.game.IGame;
import pointcards.game.IPhase;
import pointcards.game.Player;
import pointcards.game.pointsalad.phases.InitPhase;
import pointcards.io.input.IInput;
import pointcards.settings.GameSettings;
import pointcards.settings.OptionalGameSettings;

public class Game implements IGame {
    // private PSGameState state;
    private IPhase startPhase;

    public Game(List<Card> cards, List<Player> players) {
        // this.startPhase = new InitPhase();
    }

    public Game(List<Card> cards, List<Player> players, List<Bot> bots) {
        // this.startPhase = new InitPhase();
    }

    @Override
    public void run() {
        Optional<IPhase> phase = startPhase.run();

        // Play the game until we have no more phases at which point we have finished
        // playing the game.
        while (!phase.isEmpty()) {
            phase = phase.get().run();
        }
    }
}
