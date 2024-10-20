package pointcards.network;

/**
 * The `INetworkClient` interface defines the methods that a network client must
 * implement.
 * It provides methods for sending and receiving messages, as well as closing
 * the connection.
 */
public interface INetworkClient {
    /**
     * Receives a message from the server.
     * 
     * @return The received message as a string.
     */
    public String receive();

    /**
     * Sends a message to the server.
     * 
     * @param message The message to send.
     */
    public void send(String message);

    /**
     * Closes the connection to the server.
     */
    public void close();
}