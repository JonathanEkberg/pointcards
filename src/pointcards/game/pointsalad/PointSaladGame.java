package pointcards.game.pointsalad;

import pointcards.game.BaseGame;
import pointcards.game.pointsalad.phases.InitPhase;

public class PointSaladGame extends BaseGame {
    public PointSaladGame(GameState state) {
        super(new InitPhase(state));
    }
}
