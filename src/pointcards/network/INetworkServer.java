package pointcards.network;

import java.util.List;

/**
 * The `INetworkServer` interface defines the methods that a network server must
 * implement.
 * It provides methods for starting the server, connecting clients, and stopping
 * the server.
 */
public interface INetworkServer {
    /**
     * Starts the server on the specified port.
     * 
     * @param port The port number to start the server on.
     */
    public void start(int port);

    /**
     * Connects the specified number of clients to the server.
     * 
     * @param numberOfClients The number of clients to connect.
     * @return A list of connected clients.
     */
    public List<INetworkClient> connectClients(int numberOfClients);

    /**
     * Stops the server and closes all client connections.
     */
    public void stop();
}