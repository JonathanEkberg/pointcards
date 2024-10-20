package pointcards;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import pointcards.utils.Logger;

/**
 * The GameClient class represents a client that connects to a game server.
 * It handles sending and receiving messages to and from the server.
 * 
 * <p>
 * Usage example:
 * 
 * <pre>
 * {@code
 * GameClient client = new GameClient("localhost", 12345);
 * }
 * </pre>
 * 
 * <p>
 * This class starts a thread to read messages from the server and uses the main
 * thread to handle user input.
 * 
 * <p>
 * Fields:
 * <ul>
 * <li>{@code in} - An ObjectInputStream to read messages from the server.</li>
 * <li>{@code out} - An ObjectOutputStream to send messages to the server.</li>
 * <li>{@code running} - A volatile boolean flag to control the running state of
 * the client.</li>
 * </ul>
 * 
 * <p>
 * Constructor:
 * <ul>
 * <li>{@code GameClient(String hostname, int port)} - Connects to the server at
 * the specified hostname and port, and starts the client.</li>
 * </ul>
 * 
 * <p>
 * Methods:
 * <ul>
 * <li>{@code private void readFromServer()} - Reads messages from the server in
 * a loop until the "end" message is received or an exception occurs.</li>
 * <li>{@code private void sendToServer(String message)} - Sends a non-null and
 * non-empty message to the server.</li>
 * </ul>
 * 
 * @param hostname The hostname of the server to connect to.
 * @param port     The port number of the server to connect to.
 * @throws IOException If an I/O error occurs when creating the socket or
 *                     streams.
 */
public class GameClient {
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private volatile boolean running = true;

    public GameClient(String hostname, int port) throws IOException {
        Logger.info("Connecting to " + hostname + " on port " + port);
        Socket socket = new Socket(hostname, port);
        Logger.info("Connected");
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());

        // Start a thread for reading messages from the server
        Thread readThread = new Thread(this::readFromServer);
        readThread.start();

        // Main thread handles user input
        Scanner scanner = new Scanner(System.in);
        while (running) {
            // Logger.debug("Awaiting user input");
            String message = scanner.nextLine(); // Keep accepting input from the user
            // Logger.debug("User input: " + message);
            if (running) {
                sendToServer(message); // Send input to the server
            }
        }
        scanner.close();
        Logger.info("Closing socket");
        socket.close();
    }

    private void readFromServer() {
        String data = "";
        // Logger.debug("Waiting for data...");
        while (running) {
            try {
                data = (String) this.in.readObject();
                System.out.println(data);

                if (data.toLowerCase().equals("end")) {
                    running = false; // Stop when server sends "end"
                }
            } catch (EOFException e) {
                running = false;
                break;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                running = false;
                break;
            }
        }
    }

    private void sendToServer(String message) throws IOException {
        if (message != null && !message.isEmpty()) {
            this.out.writeObject(message);
        }
    }
}
// public class GameClient {
// private final ObjectInputStream in;
// private final ObjectOutputStream out;

// public GameClient(String hostname, int port) throws IOException {
// Logger.info("Connecting to " + hostname + " on port " + port);
// Socket socket = new Socket(hostname, port);
// Logger.info("Connected");
// this.out = new ObjectOutputStream(socket.getOutputStream());
// this.in = new ObjectInputStream(socket.getInputStream());
// String data = "";
// Logger.debug("Waiting for data...");
// while (data != "end") {
// try {
// // System.out.println("Waiting for data...");
// data = (String) this.in.readObject();
// System.out.println(data);

// if (data.toLowerCase().startsWith("enter a")) {
// Scanner scanner = new Scanner(System.in);
// Logger.debug("Awaiting user input");
// String message = scanner.nextLine();
// Logger.debug("User input: " + message);
// out.writeObject(message);
// scanner.close();
// }
// // System.out.println();
// } catch (EOFException e) {
// break;
// } catch (ClassNotFoundException e) {
// e.printStackTrace();
// }
// }
// Logger.info("Closing socket");
// socket.close();
// }
// }
