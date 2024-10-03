package pointcards.game.pointsalad;

import java.util.List;

import pointcards.args.GameSettings;
import pointcards.args.OptionalGameSettings;
import pointcards.game.GameMode;
import pointcards.game.IGame;
import pointcards.game.Player;
import pointcards.io.input.IInput;

public class PointSaladGame implements IGame {
    @Override
    public GameSettings setGameSettings(final OptionalGameSettings settings, final IInput input) {
        if (settings.getNumberOfPlayers().isEmpty() || settings.getNumberOfPlayers().get() < 1
                || settings.getNumberOfPlayers().get() > 6) {
            var players = input.queryInt("Enter the number of players", 1,
                    Integer.MAX_VALUE);
            settings.setNumberOfPlayers(players);
        }

        if (settings.getNumberOfBots().isEmpty() || settings.getNumberOfBots().get() < 0
                || settings.getNumberOfBots().get() > 5) {
            var bots = input.queryInt("Enter the number of bots", 0, Integer.MAX_VALUE);
            settings.setNumberOfBots(bots);
        }
        return new GameSettings(settings);
    }

    @Override
    public void init(List<Player> players) {
        for (var player : players) {
            player.getOutput().send("Welcome to Point Salad!");
        }
    }
}
