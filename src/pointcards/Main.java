package pointcards;

import pointcards.args.Args;
import pointcards.args.GameSettings;
import pointcards.args.OptionalGameSettings;
import pointcards.game.GameFactory;
import pointcards.game.GameMode;
import pointcards.game.IGame;
import pointcards.io.input.IInput;
import pointcards.io.input.LocalConsoleInput;
import pointcards.utils.Logger;

public class Main {
    public static void main(final String[] arguments) {
        final var args = Args.parseArgs(arguments);
        Logger.debug(args);

        if (args.getIsServer()) {
            runGameServer(args);
        } else {
            runGameClient(args);
        }
    }

    private static void runGameServer(final OptionalGameSettings args) {
        try {
            IInput input = new LocalConsoleInput();

            if (args.getGameMode().isEmpty()) {
                String[] gameModeChoices = new String[] { GameMode.POINTSALAD.name() };

                if (gameModeChoices.length == 1) {
                    args.setGameMode(GameMode.valueOf(gameModeChoices[0]));
                } else {
                    var choice = input.queryChoice("Choose game mode", gameModeChoices);
                    args.setGameMode(GameMode.valueOf(choice));
                }
            }

            final IGame game = GameFactory.createGame(args.getGameMode().get());
            final GameSettings settings = game.setGameSettings(args, input);

            final var server = new GameServer(game, settings);
            server.run();
        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }

    private static void runGameClient(final OptionalGameSettings argValues) {
        try {
            new GameClient(argValues.getHostname(), argValues.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}