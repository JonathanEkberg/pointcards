package pointcards;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import pointcards.args.GameSettings;
import pointcards.exceptions.UnimplementedGameModeException;
import pointcards.game.IGame;
import pointcards.game.Player;
import pointcards.io.input.LocalConsoleInput;
import pointcards.io.input.RemoteInput;
import pointcards.io.output.LocalConsoleOutput;
import pointcards.io.output.RemoteOutput;
import pointcards.network.Client;
import pointcards.utils.Logger;

public class GameServer {
    private final IGame game;
    private final ServerSocket socket;
    private final GameSettings args;

    private List<Client> clients = new ArrayList<>();

    public GameServer(final IGame game, final GameSettings args) throws IOException {
        Logger.info("Game server started on port " + args.getPort());
        this.game = game;
        this.socket = new ServerSocket(args.getPort());
        this.args = args;
    }

    public void run() {
        init();

        for (var client : clients) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void init() {
        try {
            List<Player> players = new ArrayList<>();
            // The server player
            players.add(new Player(new LocalConsoleInput(), new LocalConsoleOutput()));

            // Remote players (if any)
            if (args.getNumberOfPlayers() > 1) {
                connectClients(args.getNumberOfPlayers() - 1);

                for (var client : clients) {
                    var input = new RemoteInput(client);
                    var output = new RemoteOutput(client);
                    var player = new Player(input, output);
                    players.add(player);
                }
            }

            game.init(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectClients(int numberOfClients) throws IOException {
        for (int i = 0; i < numberOfClients; i++) {
            final var left = (numberOfClients - i);
            Logger.info("Waiting for " + left + " network " + (left == 1 ? "client" : "clients") + " to connect.");
            Socket clientSocket = this.socket.accept();
            Logger.debug("Got connection");
            var inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            var outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            var client = new Client(inFromClient, outToClient);
            clients.add(client);
        }
    }
}
