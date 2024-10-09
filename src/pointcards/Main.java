package pointcards;

import pointcards.game.IGame;
import pointcards.game.IGameFactory;
import pointcards.game.pointsalad.GameFactory;
import pointcards.io.input.IInput;
import pointcards.io.input.LocalConsoleInput;
import pointcards.network.IServerFactory;
import pointcards.network.tcp.TCPServerFactory;
import pointcards.settings.GameSettings;
import pointcards.settings.OptionalGameSettings;
import pointcards.settings.ProgramSettings;
import pointcards.utils.Args;

public class Main {
    public static void main(final String[] args) {
        OptionalGameSettings settings = Args.parseArgs(args);

        if (settings.getIsServer()) {
            IGameFactory gameFactory = new GameFactory(settings.getManifestPath());
            IServerFactory serverFactory = new TCPServerFactory();

            runGameServer(settings, gameFactory, serverFactory);
        } else {
            runGameClient(settings);
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