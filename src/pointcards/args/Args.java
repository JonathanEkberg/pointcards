package pointcards.args;

import pointcards.game.GameMode;

public class Args {
    public static OptionalGameSettings parseArgs(String[] args) {
        boolean isServer = true;
        String hostname = "localhost";
        int port = 3000;
        Integer numberOfPlayers = null;
        Integer numberOfBots = null;
        GameMode gameMode = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].toLowerCase().equals("-type")) {
                isServer = args[i + 1].toLowerCase().equals("server");
            } else if (args[i].toLowerCase().equals("-players")) {
                numberOfPlayers = Integer.parseInt(args[i + 1]);
            } else if (args[i].toLowerCase().equals("-bots")) {
                numberOfBots = Integer.parseInt(args[i + 1]);
            } else if (args[i].toLowerCase().equals("-hostname")) {
                hostname = args[i + 1];
            } else if (args[i].toLowerCase().equals("-port")) {
                port = Integer.parseInt(args[i + 1]);
            } else if (args[i].toLowerCase().equals("-gameType")) {
                gameMode = GameMode.valueOf(args[i + 1].toLowerCase());
            }
        }

        return new OptionalGameSettings(gameMode, isServer, hostname, port, numberOfPlayers, numberOfBots);
    }
}
