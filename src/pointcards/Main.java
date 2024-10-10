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

    private static void runGameServer(OptionalGameSettings optionalGameSettings,
            IGameFactory gameFactory, IServerFactory serverFactory) {
        try {
            new GameServer(gameFactory, serverFactory, optionalGameSettings).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runGameClient(ProgramSettings programSettings) {
        try {
            new GameClient(programSettings.getHostname(), programSettings.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}