package pointcards.game.pointsalad;

import pointcards.game.IGame;
import pointcards.game.IGameFactory;

public class PSGameFactory implements IGameFactory {
    public IGame createGame() {
        return new PSGame();
    }
}
