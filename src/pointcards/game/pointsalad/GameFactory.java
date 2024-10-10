package pointcards.game.pointsalad;

import java.util.List;

import org.json.JSONObject;

import pointcards.game.Bot;
import pointcards.game.IGameFactory;
import pointcards.game.Player;
import pointcards.game.pointsalad.manifest.JSONManifestParser;
import pointcards.io.JSONFileReader;
import pointcards.io.input.IInput;
import pointcards.settings.GameSettings;
import pointcards.settings.OptionalGameSettings;

public class GameFactory implements IGameFactory {
    final List<Card> cards;

    public GameFactory(String manifestPath) throws Exception {
        try {
            JSONFileReader reader = new JSONFileReader(manifestPath);
            System.out.println("Reading manifest file from " + manifestPath);
            JSONObject manifest = reader.readJsonFile();
            JSONManifestParser manifestParser = new JSONManifestParser(manifest);
            this.cards = manifestParser.getCards();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to read manifest file");
        }
    }

    public final GameSettings setGameSettings(final OptionalGameSettings settings, final IInput input) {
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

    public final Game createGame(final List<Player> players) {
        return new Game(this.cards, players);
    }

    public final Game createGame(final List<Player> players, final List<Bot> bots) {
        return new Game(this.cards, players, bots);
    }
}
