package pointcards.game.pointsalad;

import java.util.List;

import pointcards.game.Bot;
import pointcards.game.IGame;
import pointcards.game.Player;
import pointcards.io.input.IInput;
import pointcards.settings.GameSettings;
import pointcards.settings.OptionalGameSettings;

public class PSGame implements IGame {
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

    @Override
    public void init(List<Player> players, List<Bot> bots) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }
}
