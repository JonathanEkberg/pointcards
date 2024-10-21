package pointcards.network.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pointcards.network.INetworkClient;

/**
 * The `TCPClient` class implements the `INetworkClient` interface using TCP for
 * communication.
 * It provides methods for sending and receiving messages, as well as closing
 * the connection.
 */
public class TCPClient implements INetworkClient {
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    /**
     * Constructs a new `TCPClient` instance with the specified input and output
     * streams.
     * 
     * @param in  The input stream for receiving messages.
     * @param out The output stream for sending messages.
     * @throws IOException If an I/O error occurs during initialization.
     */
    public TCPClient(ObjectInputStream in, ObjectOutputStream out) throws IOException {
        this.in = in;
        this.out = out;
    }

    /**
     * Sends a message to the server.
     * 
     * @param message The message to send.
     */
    @Override
    public void send(String message) {
        try {
            this.out.writeObject(message);
        } catch (IOException e) {
        }
    }

    /**
     * Receives a message from the server.
     * 
     * @return The received message as a string.
     */
    @Override
    public String receive() {
        try {
            return (String) this.in.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Closes the connection to the server.
     */
    @Override
    public void close() {
        try {
            this.in.close();
            this.out.close();
        } catch (IOException e) {
        }
    }
}