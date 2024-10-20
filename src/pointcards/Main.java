package pointcards;

import pointcards.game.IGameFactory;
import pointcards.game.pointsalad.GameFactory;
import pointcards.network.IServerFactory;
import pointcards.network.tcp.TCPServerFactory;
import pointcards.settings.OptionalGameSettings;
import pointcards.settings.ProgramSettings;
import pointcards.utils.Args;

public class Main {
    public static void main(final String[] args) {
        try {
            OptionalGameSettings settings = Args.parseArgs(args);

            if (settings.getIsServer()) {
                IGameFactory gameFactory = new GameFactory(settings.getManifestPath());
                IServerFactory serverFactory = new TCPServerFactory();

                runGameServer(settings, gameFactory, serverFactory);
            } else {
                runGameClient(settings);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to start the game");
        }
    }

    /**
     * Runs the game server with the provided game settings, game factory, and
     * server factory.
     *
     * @param optionalGameSettings Optional settings for the game.
     * @param gameFactory          Factory to create game instances.
     * @param serverFactory        Factory to create server instances.
     */
    private static void runGameServer(OptionalGameSettings optionalGameSettings,
            IGameFactory gameFactory, IServerFactory serverFactory) {
        try {
            new GameServer(gameFactory, serverFactory, optionalGameSettings).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes and runs the GameClient with the specified program settings.
     *
     * @param programSettings the settings containing the hostname and port for the
     *                        GameClient
     */
    private static void runGameClient(ProgramSettings programSettings) {
        try {
            new GameClient(programSettings.getHostname(), programSettings.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}