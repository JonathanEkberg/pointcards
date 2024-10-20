package pointcards.utils;

import pointcards.settings.OptionalGameSettings;

/**
 * The Args class provides a method to parse command-line arguments for
 * configuring game settings.
 * It supports both server and client configurations.
 */
public class Args {
    /**
     * Parses command-line arguments to configure game settings.
     *
     * @param args the command-line arguments
     *             --type [server|client] : Specifies whether the application is
     *             running as a server or client. Default is server.
     *             --hostname <hostname> : Specifies the hostname. Default is
     *             "localhost".
     *             --port <port> : Specifies the port number. Default is 3000.
     *             --manifest-path <path> : Specifies the path to the manifest file.
     *             Required when running as a server.
     *             --players <number> : Specifies the number of players. Only
     *             applicable when running as a server.
     *             --bots <number> : Specifies the number of bots. Only applicable
     *             when running as a server.
     *
     * @return an OptionalGameSettings object containing the parsed settings
     */
    public static OptionalGameSettings parseArgs(String[] args) {
        boolean isServer = true;
        String hostname = "localhost";
        int port = 3000;

        for (int i = 0; i < args.length; i++) {
            if (args[i].toLowerCase().equals("--type")) {
                isServer = args[i + 1].toLowerCase().equals("server");
            } else if (args[i].toLowerCase().equals("--hostname")) {
                hostname = args[i + 1];
            } else if (args[i].toLowerCase().equals("--port")) {
                port = Integer.parseInt(args[i + 1]);
            }
        }

        if (!isServer) {
            return new OptionalGameSettings(isServer, hostname, port, null, null, null);
        }

        String manifestPath = null;
        Integer numberOfPlayers = null;
        Integer numberOfBots = null;

        if (isServer) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].toLowerCase().equals("--manifest-path")) {
                    manifestPath = new String(args[i + 1]);
                } else if (args[i].toLowerCase().equals("--players")) {
                    numberOfPlayers = Integer.parseInt(args[i + 1]);
                } else if (args[i].toLowerCase().equals("--bots")) {
                    numberOfBots = Integer.parseInt(args[i + 1]);
                }
            }
        }

        if (manifestPath == null) {
            System.out.println("Manifest path argument (--manifest-path) is required when running server.");
            System.exit(0);
        }

        return new OptionalGameSettings(isServer, hostname, port, numberOfPlayers, numberOfBots, manifestPath);
    }
}
