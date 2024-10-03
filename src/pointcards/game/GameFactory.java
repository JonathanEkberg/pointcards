package pointcards.game;

import pointcards.exceptions.UnimplementedGameModeException;
import pointcards.game.pointsalad.PointSaladGame;

public class GameFactory {
    public static IGame createGame(GameMode mode) throws UnimplementedGameModeException {
        if (mode == GameMode.POINTSALAD) {
            return new PointSaladGame();
        }

        throw new UnimplementedGameModeException();
    }
}
