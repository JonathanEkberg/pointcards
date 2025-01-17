package pointcards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pointcards.game.BaseBot;
import pointcards.game.BasePlayer;
import pointcards.game.IGame;
import pointcards.game.IGameFactory;
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

/**
 * The GameServer class is responsible for initializing and running a game
 * server.
 * It sets up the game and network server, and manages the players and bots.
 */
public class GameServer {
    private final IGame game;
    private final INetworkServer server;

    /**
     * Constructs a new GameServer instance.
     *
     * @param factory       the factory to create game-related objects
     * @param serverFactory the factory to create server-related objects
     * @param settings      the optional game settings
     * @throws IOException wif an I/O error occurs during server start
     */
    public GameServer(IGameFactory factory, IServerFactory serverFactory, OptionalGameSettings settings)
            throws IOException {
        IInput input = new LocalConsoleInput();
        this.server = serverFactory.createServer();
        this.server.start(settings.getPort());
        Logger.info("Game server started on port " + settings.getPort());
        GameSettings gameSettings = factory.setGameSettings(settings, input);
        this.game = initGame(factory, gameSettings);
    }

    private IGame initGame(IGameFactory factory, GameSettings settings) {
        final List<BasePlayer> players = new ArrayList<>(settings.getNumberOfPlayers());

        int playerId = 1;
        if (settings.getNumberOfPlayers() > 0) {
            players.add(new BasePlayer(String.valueOf(playerId++), new LocalConsoleInput(), new LocalConsoleOutput()));
        }

        // Remote players (if any)
        if (settings.getNumberOfPlayers() > 1) {
            List<INetworkClient> clients = this.server.connectClients(settings.getNumberOfPlayers() - 1);

            for (INetworkClient client : clients) {
                IInput input = new RemoteInput(client);
                IOutput output = new RemoteOutput(client);
                BasePlayer player = new BasePlayer(String.valueOf(playerId++), input, output);
                players.add(player);
            }
        }

        if (settings.getNumberOfBots() == 0) {
            return factory.createGame(players);
        }

        final List<BaseBot> bots = new ArrayList<>(settings.getNumberOfBots());

        for (int i = 0; i < settings.getNumberOfBots(); i++) {
            BaseBot bot = new BaseBot(String.format("Bot %d", i + 1));
            bots.add(bot);
        }

        return factory.createGame(players, bots);
    }

    /**
     * Executes the game logic by invoking the run method of the game instance.
     */
    public void run() {
        this.game.run();
    }

    /**
     * Closes the server and stops the game.
     */
    public void close() {
        this.server.stop();
    }

    public static class ExitGameException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public ExitGameException(String message) {
            super(message);
        }
    }
}
