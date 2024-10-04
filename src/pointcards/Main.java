package pointcards;

import pointcards.game.IGame;
import pointcards.game.IGameFactory;
import pointcards.game.pointsalad.PSGameFactory;
import pointcards.io.input.IInput;
import pointcards.io.input.LocalConsoleInput;
import pointcards.settings.GameSettings;
import pointcards.settings.OptionalGameSettings;
import pointcards.settings.ProgramSettings;
import pointcards.utils.Args;
import pointcards.utils.Logger;

public class Main {
    public static void main(final String[] args) {
        final IGameFactory factory = new PSGameFactory();
        final var settings = Args.parseArgs(args);
        Logger.debug(settings);

        if (settings.getIsServer()) {
            runGameServer(settings, factory);
        } else {
            runGameClient(settings);
        }
    }

    private static void runGameServer(final OptionalGameSettings optionalGameSettings, final IGameFactory factory) {
        try {
            final IInput input = new LocalConsoleInput();
            final IGame game = factory.createGame();
            final GameSettings settings = game.setGameSettings(optionalGameSettings, input);

            // Run the game server with the game and settings valid for the game.
            final var server = new GameServer(game, settings);
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runGameClient(final ProgramSettings programSettings) {
        try {
            new GameClient(programSettings.getHostname(), programSettings.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}