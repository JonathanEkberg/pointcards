package pointcards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pointcards.game.Bot;
import pointcards.game.IGame;
import pointcards.game.IGameFactory;
import pointcards.game.Player;
import pointcards.io.input.IInput;
import pointcards.io.input.LocalConsoleInput;
import pointcards.io.input.RemoteInput;
import pointcards.io.output.IOutput;
import pointcards.io.output.LocalConsoleOutput;
import pointcards.io.output.RemoteOutput;
import pointcards.network.INetworkClient;
import pointcards.network.INetworkServer;
import pointcards.network.IServerFactory;
import pointcards.settings.GameSettings;
import pointcards.settings.OptionalGameSettings;
import pointcards.utils.Logger;

public class GameServer {
    private final IGame game;
    private final INetworkServer server;

    public GameServer(IGameFactory factory, IServerFactory serverFactory, final OptionalGameSettings settings)
            throws IOException {
        Logger.info("Game server started on port " + settings.getPort());
        final IInput input = new LocalConsoleInput();
        // this.server = new TCPServer();
        this.server = serverFactory.createServer();
        this.server.start(settings.getPort());
        final GameSettings gameSettings = factory.setGameSettings(settings, input);
        this.game = initGame(factory, gameSettings);
    }

    private IGame initGame(final IGameFactory factory, final GameSettings settings) throws IOException {
        final List<Player> players = new ArrayList<>(settings.getNumberOfPlayers());
        // The server player. Always at least one.
        players.add(new Player(new LocalConsoleInput(), new LocalConsoleOutput()));

        // Remote players (if any)
        if (settings.getNumberOfPlayers() > 1) {
            List<INetworkClient> clients = this.server.connectClients(settings.getNumberOfPlayers() - 1);

            for (INetworkClient client : clients) {
                IInput input = new RemoteInput(client);
                IOutput output = new RemoteOutput(client);
                Player player = new Player(input, output);
                players.add(player);
            }
        }

        if (settings.getNumberOfBots() == 0) {
            return factory.createGame(players);
        }

        final List<Bot> bots = new ArrayList<>(settings.getNumberOfBots());

        for (int i = 0; i < settings.getNumberOfBots(); i++) {
            var bot = new Bot();
            bots.add(bot);
        }

        return factory.createGame(players, bots);
    }

    public void run() {
        this.game.run();
    }
}
