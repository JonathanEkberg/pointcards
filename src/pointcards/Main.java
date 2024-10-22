package pointcards;

import java.io.IOException;
import java.net.UnknownHostException;

import pointcards.GameServer.ExitGameException;
import pointcards.game.IGameFactory;
import pointcards.game.pointsalad.GameFactory;
import pointcards.network.IServerFactory;
import pointcards.network.tcp.TCPServerFactory;
import pointcards.settings.OptionalGameSettings;
import pointcards.settings.ProgramSettings;
import pointcards.utils.Args;
import pointcards.utils.Logger;

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
            System.err.println("Game crashed");
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
            GameServer server = new GameServer(gameFactory, serverFactory, optionalGameSettings);

            try {
                server.run();
            } catch (ExitGameException e) {
                Logger.info("Closing game server. Reason: " + e.getMessage());
            }

            server.close();
        } catch (IOException e) {
            System.err.println("Failed to start the game server on " + optionalGameSettings.getHostname() + ":"
                    + optionalGameSettings.getPort() + ". Error: " + e.getMessage());
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
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + programSettings.getHostname());
        } catch (IOException e) {
            System.err.println("Failed to connect to the server at " + programSettings.getHostname()
                    + ":" + programSettings.getPort() + ". " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Failed to start the game client. Error: " + e.getMessage());
        }
    }
}