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

/**
 * The `TCPServer` class implements the `INetworkServer` interface using TCP for
 * communication.
 * It provides methods for starting the server, connecting clients, and stopping
 * the server.
 */
public class TCPServer implements INetworkServer {
    private ServerSocket socket;
    private List<INetworkClient> clients;

    /**
     * Starts the server on the specified port.
     * 
     * @param port The port number to start the server on.
     * @throws IOException If an I/O error occurs during initialization.
     */
    @Override
    public void start(int port) throws IOException {
        this.socket = new ServerSocket(port);
    }

    /**
     * Connects the specified number of clients to the server.
     * 
     * @param numberOfClients The number of clients to connect.
     * @return A list of connected clients.
     */
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

    /**
     * Stops the server and closes all client connections.
     */
    @Override
    public void stop() {
        try {
            for (INetworkClient client : this.clients) {
                client.close();
            }
            this.socket.close();
        } catch (IOException e) {
            // TODO: Update error handling
        }
    }
}