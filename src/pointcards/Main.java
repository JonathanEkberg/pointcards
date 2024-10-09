package pointcards;

import pointcards.game.IGame;
import pointcards.game.IGameFactory;
import pointcards.game.pointsalad.GameFactory;
import pointcards.io.input.IInput;
import pointcards.io.input.LocalConsoleInput;
import pointcards.settings.GameSettings;
import pointcards.settings.OptionalGameSettings;
import pointcards.settings.ProgramSettings;
import pointcards.utils.Args;

public class Main {
    public static void main(final String[] args) {
        OptionalGameSettings settings = Args.parseArgs(args);
        IGameFactory gameFactory = new GameFactory(settings.getManifestPath());
        runGame(settings, gameFactory);
    }

    private static void runGame(OptionalGameSettings settings, IGameFactory gameFactory) {
        if (settings.getIsServer()) {
            runGameServer(settings, gameFactory);
        } else {
            runGameClient(settings);
        }
    }

    private static void runGameServer(OptionalGameSettings optionalGameSettings,
            IGameFactory factory) {
        try {
            new GameServer(factory, optionalGameSettings).run();
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