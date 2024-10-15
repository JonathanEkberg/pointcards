package pointcards.network.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import pointcards.network.INetworkClient;
import pointcards.network.INetworkServer;
import pointcards.utils.Logger;

public class TCPServer implements INetworkServer {
    private ServerSocket socket;
    private List<INetworkClient> clients;

    @Override
    public void start(int port) {
        try {
            this.socket = new ServerSocket(port);
        } catch (IOException e) {
            // TODO: Update error handling
            e.printStackTrace();
        }
    }

    @Override
    public List<INetworkClient> connectClients(int numberOfClients) {
        try {
            List<INetworkClient> clients = new ArrayList<>();

            for (int i = 0; i < numberOfClients; i++) {
                final var left = (numberOfClients - i);
                Logger.info("Waiting for " + left + " network " + (left == 1 ? "client" : "clients") + " to connect.");
                Socket clientSocket = this.socket.accept();
                Logger.debug("Got connection");
                var inFromClient = new ObjectInputStream(clientSocket.getInputStream());
                var outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                var client = new TCPClient(inFromClient, outToClient);
                clients.add(client);
            }

            this.clients = clients;
            return clients;
        } catch (IOException e) {
            // TODO: Update error handling
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void stop() {
        try {
            for (INetworkClient client : this.clients) {
                client.close();
            }
            this.socket.close();
        } catch (IOException e) {

        }
    }
}
