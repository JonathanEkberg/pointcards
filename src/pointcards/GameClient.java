package pointcards;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import pointcards.utils.Logger;

public class GameClient {
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    public GameClient(String hostname, int port) throws IOException {
        Logger.info("Connecting to " + hostname + " on port " + port);
        Socket socket = new Socket(hostname, port);
        Logger.debug("Connected");
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        String data = "";
        Logger.debug("Waiting for data 1...");
        while (data != "end") {
            try {
                System.out.println("Waiting for data...");
                data = (String) this.in.readObject();
                System.out.println(data);
                System.out.println();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        Logger.debug("Closing socket");
        socket.close();
    }
}
