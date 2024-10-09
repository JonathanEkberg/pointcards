package pointcards.utils;

import pointcards.settings.OptionalGameSettings;

public class Args {
    public static OptionalGameSettings parseArgs(String[] args) {
        boolean isServer = true;
        String hostname = "localhost";
        int port = 3000;
        Integer numberOfPlayers = null;
        Integer numberOfBots = null;
        String manifestPath = null;

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
            }
        }

        if (isServer) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].toLowerCase().equals("-manifestPath")) {
                    manifestPath = new String(args[i + 1]);
                }
            }
        }

        return new OptionalGameSettings(isServer, hostname, port, numberOfPlayers, numberOfBots, manifestPath);
    }
}
