package pointcards.network;

import java.io.EOFException;

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
     * @throws EOFException If the end of the stream is reached.
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