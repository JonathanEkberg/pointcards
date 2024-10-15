package pointcards.game.pointsalad;

import pointcards.game.AbstractGame;
import pointcards.game.pointsalad.phases.InitPhase;

public class PointSaladGame extends AbstractGame {
    public PointSaladGame(GameState state) {
        super(new InitPhase(state));
    }
}
